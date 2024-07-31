package com.joueurs.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.dto.ImageSourceCreateDTO;
import com.joueurs.api.dto.ImageSourceDTO;
import com.joueurs.api.service.IImageSourceService;
import com.joueurs.api.utils.ConstanteApp;
import com.joueurs.api.utils.PaginationImageSourceResponse;


import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
@RequestMapping("api/image")
public class ImageSourceController  {
	
	
	private IImageSourceService imageSourceService;
	
	public ImageSourceController (IImageSourceService imageSourceService) {
		this.imageSourceService = imageSourceService;
	}
	
	
	@GetMapping("/all")
	@Operation(summary = "Get ALL Images", description = "This endpoint retrieve all images")
	public PaginationImageSourceResponse getAllImagesSources(
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY_FILENAME,required=false) String sortBy){
	 
	return imageSourceService.getAllImageSource(pageNo, pageSize,sortBy);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get Images By Id", description = "This endpoint retrieve Image by Id")
	public ResponseEntity<ImageSourceDTO> getImageSourceById(@PathVariable("id") int imageSourceId){
		
		return new ResponseEntity<>(imageSourceService.findImageSourceById(imageSourceId), HttpStatus.OK);
		}
	
	
	@PostMapping("/add")
	@Operation(summary = "Create ImageSource", description = "This endpoint create a imageSource") // Documentation Swagger
	public ResponseEntity<ImageSourceDTO> createImageSource(@RequestBody ImageSourceCreateDTO imageSourceCreateDto){
		
		return new ResponseEntity<>(imageSourceService.createImageSource(imageSourceCreateDto), HttpStatus.CREATED);	
	}
	
	
	
		
	

}
