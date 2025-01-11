package com.faceboot.comment_service.client;


import com.faceboot.comment_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(value = "user",url="http://localhost:8081")
public interface UserClient {
        @RequestMapping(method= RequestMethod.GET,value="/user/byId")
        User getUserById(@RequestParam("id") Long id);
}
