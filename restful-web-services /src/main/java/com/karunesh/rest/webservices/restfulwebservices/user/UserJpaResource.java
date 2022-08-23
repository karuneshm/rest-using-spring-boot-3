package com.karunesh.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.karunesh.rest.webservices.restfulwebservices.user.jpa.PostRepository;
import com.karunesh.rest.webservices.restfulwebservices.user.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postrepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	//EntityModel
	//WebMvcLinkBuilder
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotException("user with id "+id + " not found");
		}
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link  = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retriveUserAndPost(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotException("user with id "+id + " not found");
		}
		
	    List<Post> posts = user.get().getPosts();
	    if(ObjectUtils.isEmpty(posts)) {
	    	throw new PostNotFoundException("Posts not found for user " + user.get().getName());
	    }
	    
	    return posts;
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @RequestBody Post post){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotException("user with id "+id + " not found");
		}
		
		post.setUser(user.get());
		Post savedPost = postrepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	    
	}
	
	@GetMapping("/jpa/users/{id}/posts/{postId}")
	public Post createPostForUser(@PathVariable int id, @PathVariable int postId){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotException("user with id "+id + " not found");
		}
		
		List<Post> posts = user.get().getPosts();
		
		Optional<Post> postFromDB = posts.stream().filter(post -> post.getId().equals(postId)).findFirst();
		
		if(ObjectUtils.isEmpty(posts)) {
	    	throw new PostNotFoundException("Posts not found for user " + user.get().getName());
	    }
	    
	    return postFromDB.get();
		
		
		
	    
	}

}
