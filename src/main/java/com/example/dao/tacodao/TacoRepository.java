package com.example.dao.tacodao;

import com.example.pojo.Taco;

/**
 * The interface Taco repository.
 *
 * @author lambda
 */
public interface TacoRepository {
    /**
     * Save taco.
     *
     * @param design the design
     * @return the taco
     */
    Taco save(Taco design);
}
