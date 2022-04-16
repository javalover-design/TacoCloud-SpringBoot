package com.example.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author lambda
 */
@Data
public class Taco {
    private  Long id;
    private Date createAt;
    @NotNull
    @Size(min = 5,message = "名字最少要5个字")
    private String name;
    @Size(min = 1,message = "至少选择一种")
    private List<String> ingredients;
}
