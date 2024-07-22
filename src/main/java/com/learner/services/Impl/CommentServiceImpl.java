package com.learner.services.Impl;

import com.learner.entities.Comment;
import com.learner.entities.Post;
import com.learner.exceptions.ResourceNotFoundException;
import com.learner.payLoad.CommentDto;
import com.learner.repositories.CommentRepo;
import com.learner.repositories.PostRepo;
import com.learner.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto create(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment comment1 = commentRepo.save(comment);

        return modelMapper.map(comment1,CommentDto.class);
    }

    @Override
    public void delete(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","CommentId",commentId));
        commentRepo.delete(comment);
    }
}
