package com.learner.payLoad;

import com.learner.entities.Category;
import com.learner.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private String postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
}
