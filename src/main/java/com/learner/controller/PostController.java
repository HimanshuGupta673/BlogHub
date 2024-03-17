package com.learner.controller;

import com.learner.config.AppConstants;
import com.learner.payLoad.ApiResponse;
import com.learner.payLoad.PostDto;
import com.learner.payLoad.PostResponse;
import com.learner.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto createPost = postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> posts = postService.getPostByUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, @RequestParam(value = "pageSize",defaultValue =AppConstants.PAGE_SIZE,required = false ) Integer pageSize, @RequestParam(value = "sortBy",defaultValue =AppConstants.SORT_BY,required = false ) String sortBy){
        PostResponse posts = postService.getAllPost(pageNumber,pageSize,sortBy);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ApiResponse("Post is deleted successfully",true);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatedPost = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> search(@PathVariable String keywords){
        List<PostDto> postDto = postService.searchPosts(keywords);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
}
