package com.example.controller;

import com.example.dao.ingredientdao.JdbcIngredientRepository;
import com.example.pojo.Ingredient;
import com.example.pojo.Ingredient.Type;
import com.example.pojo.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lambda
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    /**将dao层对象注入到controller，以便controller直接调用dao层*/
    private final JdbcIngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(JdbcIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @RequestMapping
    public String showDesignForm(Model model){
        //List<Ingredient> ingredients= Arrays.asList(
        //        new Ingredient("FLTO","Flour Tortilla", Ingredient.Type.WRAP),
        //        new Ingredient("COTO","Corn Tortilla", Ingredient.Type.WRAP),
        //        new Ingredient("GRBF","Ground Beef", Ingredient.Type.PROTEIN),
        //        new Ingredient("CARN","Carnitas", Ingredient.Type.PROTEIN),
        //        new Ingredient("TMTO","Diced Tomatoes", Ingredient.Type.VEGGIES),
        //        new Ingredient("LETC","Lettuce", Ingredient.Type.VEGGIES),
        //        new Ingredient("CHED","Cheddar", Ingredient.Type.CHEESE),
        //        new Ingredient("JACK","Monterrey Jack", Ingredient.Type.CHEESE),
        //        new Ingredient("SLSA","Salsa", Ingredient.Type.SAUCES),
        //        new Ingredient("SRCR","Sour Cream", Ingredient.Type.SAUCES)
        //);
        //model.addAttribute("design",new Taco());

        List<Ingredient> ingredients = new ArrayList<>();
        //此处调用查找所有的方法获取到所有信息，之后进行遍历。将获取到的对象依次加入集合
        ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));

        Type[] types=Ingredient.Type.values();
        for (Type type:types){
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
        }
        return "design";

    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors){
        if (errors.hasErrors()){
            return "design";
        }
        log.info("processing design"+design);
        return "redirect:/orders/current";
    }

    private  List<Ingredient>filterByType(List<Ingredient> ingredients,Type type){
        return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }
}
