package com.example.dao.tacodao;
import com.example.pojo.Ingredient;
import com.example.pojo.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
/**
 * @author lambda
 */
@Repository
public class JdbcTacoRepository implements TacoRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 传入一个产品，获取这个产品的主键
     * @param taco
     * @return
     */
    private long saveTacoInfo(Taco taco){
        taco.setCreateAt(new Date());
        PreparedStatementCreator psc=new PreparedStatementCreatorFactory("INSERT INTO Taco(name,createdAt)VALUES (?,?)",
                Types.VARCHAR,Types.TIMESTAMP)
                .newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                new Timestamp(taco.getCreateAt().getTime())
                        )
                );

        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(psc,keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * 完善产品与配料的映射
     * @param ingredient
     * @param tacoId
     */
    private void saveIngredientToTaco(Ingredient ingredient,long tacoId){
        jdbcTemplate.update(
                "INSERT INTO Taco_Ingredients (taco, ingredient) VALUES (?,?)"
                ,tacoId,ingredient.getId()
        );
    }

    /**
     * 保存设计好的产品
     * @param design the design
     * @return
     */
    @Override
    public Taco save(Taco design) {
        long tacoId = saveTacoInfo(design);
        design.setId(tacoId);
        for (Ingredient ingredient:design.getIngredients()){
            saveIngredientToTaco(ingredient,tacoId);
        }
        return design;
    }
}
