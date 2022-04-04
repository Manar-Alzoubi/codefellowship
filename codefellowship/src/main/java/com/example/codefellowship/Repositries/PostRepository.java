package com.example.codefellowship.Repositries;

import com.example.codefellowship.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
