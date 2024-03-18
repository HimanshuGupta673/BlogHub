package com.learner.services;

import com.learner.payLoad.CommentDto;

public interface CommentService {
    CommentDto create(CommentDto commentDto,Integer postId);

    void delete(Integer commentId);
}
