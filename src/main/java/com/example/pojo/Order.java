package com.example.pojo;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lambda
 */
@Data
public class Order {
    @NotBlank(message = "名字是必须的")
    private String name;
    @NotBlank(message = "地址是必须的")
    private String street;
    @NotBlank(message = "城市是必须的")
    private  String city;
    @NotBlank(message = "州是必须的")
    private String state;
    @NotBlank(message = "Zip是必须的")
    private String zip;
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",message = "必须正确")
    private String ccExpiration;
    @Digits(integer = 3,fraction = 0,message = "必须合法")
    private String ccCVV;
    private Long id;
    private Date placedAt;
    private List<Taco> tacos=new ArrayList<>();
    public void addDesign(Taco design){
        this.tacos.add(design);
    }



}
