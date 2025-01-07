package com.faceboot.comment_service.repository;

import com.faceboot.comment_service.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


//@RepositoryRestResource
public interface CommentRepository extends MongoRepository<Comment,String> {

    public List<Comment> findCommentsByPostId(long postId);
    public void deleteCommentsByPostId(long postId);
    public Comment findCommentById(String id);

}
