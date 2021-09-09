package com.test.controller;

import com.test.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @RequestMapping("product")
    public String product(int num){
        List<Product> list = new ArrayList<>();
        for (int i =0;i<num;i++){
            list.add(new Product("product"+i,i));
        }
        return list.toString();
    }

}
