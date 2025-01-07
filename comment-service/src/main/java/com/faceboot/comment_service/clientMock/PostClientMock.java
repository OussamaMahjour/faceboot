package com.faceboot.comment_service.clientMock;

import com.faceboot.comment_service.client.PostClient;
import com.faceboot.comment_service.model.Post;
import com.faceboot.comment_service.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class PostClientMock implements PostClient {

    @Override
    public Post getPostById(Long id) {
        User user = new User().builder()
                .id(1L)
                .name("oussama")
                .createdAt(LocalDateTime.now())
                .email("oussama@gmail.com")
                .deletedAt(null)
                .gender("male")
                .password("123456")
                .verified(false)
                .build();
        Post post = Post.builder()
                .votes(id)
                .user(user)
                .medias(null)
                .createdAt(LocalDateTime.now())
                .id(1L)
                .build();
        return post;
    }
}
