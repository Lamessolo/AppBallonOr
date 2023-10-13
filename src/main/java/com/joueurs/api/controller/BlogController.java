package com.joueurs.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.dto.BlogCreateDTO;
import com.joueurs.api.dto.BlogDTO;
import com.joueurs.api.service.IBlogService;
import com.joueurs.api.utils.ConstanteApp;
import com.joueurs.api.utils.PaginationBlogResponse;
import com.joueurs.api.utils.PaginationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/blog")
@CrossOrigin
public class BlogController {

	private final IBlogService blogService;
	
	@PostMapping("/add")
	public ResponseEntity<BlogDTO> createBlog(@RequestBody BlogCreateDTO blogCreateDto){		
		BlogDTO newBlog = blogService.createBlogPost(blogCreateDto);
		return new ResponseEntity<>(newBlog, HttpStatus.CREATED);	
	}
	
	@GetMapping("/all")
	public PaginationBlogResponse getAllBlogs(
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY_TITLE,required=false) String sortBy){
	 
	return blogService.getAllblogs(pageNo,pageSize,sortBy);
	}
	
}
