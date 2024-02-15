package com.joueurs.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.joueurs.api.dto.SelectionCreateDTO;
import com.joueurs.api.dto.SelectionDTO;
import com.joueurs.api.service.ISelectionService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/selection")
@CrossOrigin
public class SelectionController {

	private final ISelectionService selectionService;
	
	
	
	@GetMapping("/all")
	@Operation(summary = "Get All Selection", description = "This endpoint retrieve selections")
	public List<SelectionDTO> getAllSelections(){
		 
		return selectionService.getAllSelection();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get Selection By Id", description = "This endpoint retrieve selection by Id")
	public ResponseEntity<SelectionDTO> getSelectionById(@PathVariable("id") int selectionId){
		
	return new ResponseEntity<>(selectionService.findSelectionById(selectionId), HttpStatus.OK);
		}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String,Boolean>> deleteSelection(@PathVariable("id") int selectionId){		
		return new ResponseEntity<>(selectionService.deleteSelection(selectionId), HttpStatus.OK);
	}
	
	@PostMapping("/{add}")
	public ResponseEntity<SelectionDTO> createSelection(@RequestBody SelectionCreateDTO selectionCreateDto){		
			
		return new ResponseEntity<>(selectionService.createSelection(selectionCreateDto),HttpStatus.CREATED);	
	}
	
	
	@GetMapping("conf/{confederationName}")
	@Operation(summary = "Get Selection By Confederation Name", description = "This endpoint retrieve selection by Confederation Name")
	public ResponseEntity<List<SelectionDTO>> getSelectionByConfederationName(@PathVariable("confederationName") String confederationName){
		
	return new ResponseEntity<>(selectionService.findSelectionByConfederation(confederationName), HttpStatus.OK);
		}
	
	/*
	@GetMapping("selection/confederation}")
	public PaginationSelectionResponse getSelectionByConfederation(@PathVariable("conf")String confederation,
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy)
	{ 
		return selectionService.findSelectionByConfederation(confederation,pageNo,pageSize,sortBy);
	}*/
	
	@PutMapping("{id}")
	@Operation(summary = "Update Selection", description = "This endpoint update une selection")
	public ResponseEntity<SelectionDTO> updateSelection(@PathVariable("id") int selectionId,@RequestBody SelectionCreateDTO selectionCreateDto)
	{				 
			return new ResponseEntity<>(selectionService.updateSelection(selectionId,selectionCreateDto),HttpStatus.OK);		
	}
	
	
}
