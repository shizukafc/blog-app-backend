package com.codewithdurgesh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.config.AppConstants;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/users/{userId}/category/{categoryId}/posts/")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, 
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
				
				PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
				
				return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/users/{userId}/posts/")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		
		List<PostDto> postsbyUser=this.postService.getAllPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(postsbyUser,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts/")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> postsbyCategory=this.postService.getAllPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(postsbyCategory,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR,required=false) String sortDir)
			
	{
		
	PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
	
	return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("posts/{postId}/")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto post=this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	@DeleteMapping("posts/{postId}/")
	public ApiResponse deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		
		return new ApiResponse("Post is successfully deleted",true);
		
	}
	
	@PutMapping("/posts/{postId}/")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords")String keywords){
		List<PostDto> result=this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	

}
