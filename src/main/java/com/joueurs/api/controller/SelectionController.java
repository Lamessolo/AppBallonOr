package com.joueurs.api.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.dto.JoueurCreateDTO;
import com.joueurs.api.dto.JoueurDTO;
import com.joueurs.api.dto.SelectionCreateDTO;
import com.joueurs.api.dto.SelectionDTO;
import com.joueurs.api.exception.JoueurNotFoundException;
import com.joueurs.api.service.ISelectionService;
import com.joueurs.api.utils.ConstanteApp;
import com.joueurs.api.utils.PaginationSelectionResponse;


import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/selection")
@CrossOrigin
public class SelectionController {

	private final ISelectionService selectionService;
	
	@GetMapping("/all")
	public PaginationSelectionResponse getAllSelections(
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy){
		 
		return selectionService.getAllSelection(pageNo,pageSize,sortBy);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SelectionDTO> getSelectionById(@PathVariable("id") long selectionId){
		
		SelectionDTO selection = selectionService.findSelectionById(selectionId);
		return new ResponseEntity<>(selection, HttpStatus.OK);
		}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String,Boolean>> deleteSelection(@PathVariable("id") long selectionId){		
		return new ResponseEntity<>(selectionService.deleteSelection(selectionId), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SelectionDTO> createSelection(@RequestBody SelectionCreateDTO selectionCreateDto){		
		SelectionDTO newSelection = selectionService.createSelection(selectionCreateDto);
		return new ResponseEntity<>(newSelection, HttpStatus.CREATED);	
	}
	
	
}
