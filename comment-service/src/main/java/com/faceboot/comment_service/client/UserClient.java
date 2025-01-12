package com.faceboot.comment_service.client;


import com.faceboot.comment_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(value = "user",url="http://gateway-service:8080/USER-SERVICE/api/v1")
public interface UserClient {
        @RequestMapping(method= RequestMethod.GET,value="/users/{id}")
        User getUserById(@PathVariable("id") Long id);
}
