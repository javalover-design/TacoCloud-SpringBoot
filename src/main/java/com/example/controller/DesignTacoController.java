package com.example.controller;

import com.example.dao.ingredientdao.IngredientRepository;
import com.example.dao.tacodao.TacoRepository;
import com.example.pojo.Ingredient;
import com.example.pojo.Ingredient.Type;
import com.example.pojo.Order;
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
    private final IngredientRepository ingredientRepository;
    private  TacoRepository tacoRepository;


    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    /**
     *
     * @param model
     * @return design.html
     */
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
        Taco design = new Taco();
        model.addAttribute("design",design);

        return "design";

    }

    /**
     * 注解可以确保在模型中创建一个Order对象
     * @SessionAttributes 中的内容是order，这是因为order在每个订单请求中都能够出现，必须要放在session中才能跨请求
     * 使用
     *
     * @return order
     */
    @ModelAttribute(name = "order")
   public Order order(){
        return new Order();
    }

    /**
     * 确保在模型中创建一个taco对象
     * @return taco
     */
    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }


    /**
     *
     * @param  design
     * @param  errors
     * @param order 带有 @ModelAttribute注解，表明其值来自模型中
     * @return order.html
     */
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors,@ModelAttribute Order order){
        if (errors.hasErrors()) {
            System.out.println("erro");
            return "design";
        }
        Taco save = tacoRepository.save(design);
        order.addDesign(save);
        log.info("processing design"+design);
       // model.addAttribute("design",save);
        return "redirect:/orders/current";
    }

    private  List<Ingredient> filterByType(List<Ingredient> ingredients,Type type){
        return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }
}
