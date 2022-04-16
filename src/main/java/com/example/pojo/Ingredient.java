package com.example.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author lambda
 */
@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;


    public static  enum  Type{
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCES
    }
}
