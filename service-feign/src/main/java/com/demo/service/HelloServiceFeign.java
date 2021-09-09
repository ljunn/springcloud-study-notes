package com.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider")
public interface HelloServiceFeign {
    @RequestMapping(value = "product",method = RequestMethod.POST)
    public String provide(@RequestParam("num") int num);
}
