package com.faceboot.comment_service.client;

import com.faceboot.comment_service.model.Post;
import com.faceboot.comment_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(value = "post",url="http://localhost:8081")
public interface PostClient {
    @RequestMapping(method= RequestMethod.GET,value="/post/byId")
    Post getPostById(@RequestParam("id") Long id);
}
