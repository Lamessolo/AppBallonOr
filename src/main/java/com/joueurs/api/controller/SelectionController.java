package com.joueurs.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
