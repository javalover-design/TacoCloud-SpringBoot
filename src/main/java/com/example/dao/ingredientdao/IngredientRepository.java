package com.example.dao.ingredientdao;

import com.example.pojo.Ingredient;

/**
 * The interface Ingredient repository.
 *
 * @author lambda
 */
public interface IngredientRepository {
    /**
     * Find all iterable.
     *查找所有的配料
     * @return the iterable
     */
    Iterable<Ingredient> findAll();

    /**
     * Find one ingredientdao.
     * 通过id查找
     *
     * @param id the id
     * @return the ingredientdao
     */
    Ingredient findOne(String id);

    /**
     * Save ingredientdao.
     *保存一个
     * @param ingredient the ingredientdao
     * @return the ingredientdao
     */
    Ingredient save(Ingredient ingredient);

}
