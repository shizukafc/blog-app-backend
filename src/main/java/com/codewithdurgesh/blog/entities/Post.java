package com.codewithdurgesh.blog.entities;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.codewithdurgesh.blog.payloads.CategoryDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title")
	private String title;
	
	@Column(length=10000)
	private String content;
	private String imageName;
	private Date addedDate;
	
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
	

}
