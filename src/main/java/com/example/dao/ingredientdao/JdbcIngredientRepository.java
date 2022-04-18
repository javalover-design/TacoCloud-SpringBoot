package com.example.dao.ingredientdao;

import com.example.pojo.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lambda
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository{
    /**此处需要使用jdbc模板来完成增删改查等操作*/
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {

        return jdbc.query("SELECT id,name,type FROM Ingredient",
        //        new RowMapper<Ingredient>() {
        //    @Override
        //    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        //        return null;
        //    } 此处表示将行集（查询结果一一形成ingredient对象）
        //});
        this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        // 该方法的三个参数，sql，RowMapper对象以及args
        return jdbc.queryForObject("SELECT id,name,type FROM Ingredient WHERE id=?",
                //new RowMapper<Ingredient>() {
                //    @Override
                //    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
                //        return null;
                //    }
                // 此处表示将行集映射成为对象
                //}
                this::mapRowToIngredient,id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("INSERT INTO Ingredient(id,name,type) VALUES(?,?,?)",
                ingredient.getId(),ingredient.getName(),ingredient.getType().toString());
        // 此处表示插入数据，获取前台的数据，并设置到数据库中
        return ingredient;
    }

    private  Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {

        return new Ingredient(rs.getString("id"),rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));


    }
}
