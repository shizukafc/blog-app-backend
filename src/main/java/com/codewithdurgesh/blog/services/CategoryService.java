package com.codewithdurgesh.blog.services;

import java.util.List;

//import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	public void deleteCategory(Integer categoryId);
	
	public CategoryDto getcategory(Integer categoryId);
	
	public List<CategoryDto> getAllCategory();

}
