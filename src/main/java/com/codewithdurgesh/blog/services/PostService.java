package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	PostDto deletePost(Integer postId);
	PostDto getPostById(Integer postId);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDir);
	List<PostDto> getAllPostByCategory(Integer categoryId);
	List<PostDto> getAllPostByUser(Integer userId);
	List<PostDto> searchPosts(String keyword);

}
