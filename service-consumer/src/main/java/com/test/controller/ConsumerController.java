package com.test.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    EurekaClient client;
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("consumer")
    public String consumer(int num){
        //InstanceInfo instanceInfo = client.getNextServerFromEureka("service-provider",false);
        //String url = instanceInfo.getHomePageUrl()+"/product?num="+num;
        String url = "http://service-provider/product?num="+num;
        return restTemplate.getForObject(url,String.class);
    }
}
