package com.karunesh.rest.webservices.restfulwebservices.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karunesh.rest.webservices.restfulwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
