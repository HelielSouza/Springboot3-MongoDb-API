package com.workhop.apimongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workhop.apimongo.domain.Post;
import com.workhop.apimongo.repositories.PostRepository;
import com.workhop.apimongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll(){
		return repository.findAll();
	}
	
	public Post findByID(String id) {
	    Optional<Post> user = repository.findById(id);
	    return user.orElseThrow(() -> new ObjectNotFoundException("Post not found"));
	}	
	
	public List<Post> findByTitle (String text) {
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
}
