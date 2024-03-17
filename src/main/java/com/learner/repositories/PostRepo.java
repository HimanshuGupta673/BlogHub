package com.learner.repositories;

import com.learner.entities.Category;
import com.learner.entities.Post;
import com.learner.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
  List<Post> findByUser(User user);
  List<Post> findByCategory(Category category);
  List<Post> findByTitleContaining(String title);

//  @Query("select p from Post where p.title like :key")
//  List<Post> searchByTitle(@Param("key") String title);

}
