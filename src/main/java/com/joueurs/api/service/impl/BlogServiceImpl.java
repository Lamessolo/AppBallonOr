package com.joueurs.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.joueurs.api.dto.BlogCreateDTO;
import com.joueurs.api.dto.BlogDTO;
import com.joueurs.api.entity.Blog;
import com.joueurs.api.repository.BlogRepository;
import com.joueurs.api.service.IBlogService;
import com.joueurs.api.utils.PaginationBlogResponse;

@Service
public class BlogServiceImpl implements IBlogService {

	
    private BlogRepository blogRepository;
    private ModelMapper mapper;
    
   public BlogServiceImpl (BlogRepository blogRepository,ModelMapper mapper) {
	   this.blogRepository = blogRepository;
	   this.mapper = mapper;
   }
   
   private  BlogDTO mapEntityToDto(Blog blog) 
	{		
		return mapper.map(blog, BlogDTO.class);
	}
	
	private Blog mapDtoToEntity(BlogCreateDTO blogCreateDto) 
	{		
		return mapper.map(blogCreateDto, Blog.class);
	}
    	
	@Override
	public BlogDTO createBlogPost(BlogCreateDTO blogCreateDto) {
		  
		Blog blogNew = new Blog();
		blogNew.setAuthor(blogCreateDto.getAuthor());
		blogNew.setContent(blogCreateDto.getContent());
		blogNew.setDescription(blogCreateDto.getDescription());
		blogNew.setTitle(blogCreateDto.getTitle());
		blogNew.setUrlHandle(blogCreateDto.getUrlHandle());
		blogNew.setPublishedDate(blogCreateDto.getPublishedDate());
		blogNew.setImageUrl(blogCreateDto.getImageUrl());
		blogNew.setVisible(blogCreateDto.isVisible());
			blogRepository.save(blogNew);
			
		return mapEntityToDto(blogNew);
	}

	@Override
	public PaginationBlogResponse getAllblogs(int pageNo, int pageSize, String sortBy) {
		 	 
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Blog> listeDesblogs = blogRepository.findAll(pageable);
		List<Blog> blogs = listeDesblogs.getContent();
		List<BlogDTO> content = blogs.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
		PaginationBlogResponse pageBlogResponse = new PaginationBlogResponse();
		pageBlogResponse.setContent(content);
		pageBlogResponse.setPageNo(listeDesblogs.getNumber());
		pageBlogResponse.setPageSize(listeDesblogs.getSize());
		pageBlogResponse.setTotalElements(listeDesblogs.getTotalElements());
		pageBlogResponse.setTotalPages(listeDesblogs.getTotalPages());
		pageBlogResponse.setLast(listeDesblogs.isLast());
		return pageBlogResponse;	
	}
	

}
