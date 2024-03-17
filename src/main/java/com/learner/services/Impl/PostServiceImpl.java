package com.learner.services.Impl;

import com.learner.entities.Category;
import com.learner.entities.Post;
import com.learner.entities.User;
import com.learner.exceptions.ResourceNotFoundException;
import com.learner.payLoad.PostDto;
import com.learner.payLoad.PostResponse;
import com.learner.repositories.CategoryRepo;
import com.learner.repositories.PostRepo;
import com.learner.repositories.UserRepo;
import com.learner.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));

        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatadPost = postRepo.save(post);
        return modelMapper.map(updatadPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy) {

        PageRequest p = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
//        List<Post> allPosts = postRepo.findAll();
        List<PostDto> postDto = allPosts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setLastPage(pagePost.isLast());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElements(pagePost.getTotalElements());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        List<Post> posts = postRepo.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = postRepo.findByTitleContaining(keyword);
//        List<Post> posts = postRepo.searchByTitle("%"+keywords+"%");
        List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
