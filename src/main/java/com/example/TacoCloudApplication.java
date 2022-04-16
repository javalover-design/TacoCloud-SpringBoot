package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author lambda
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class TacoCloudApplication {
/**添加相关依赖后会出错，此时在主启动类中添加注解*/
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

}
