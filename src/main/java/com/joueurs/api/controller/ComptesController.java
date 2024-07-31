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

import com.joueurs.api.dto.CompteCreateDTO;
import com.joueurs.api.dto.CompteDTO;
import com.joueurs.api.service.ICompteService;
import com.joueurs.api.utils.ConstanteApp;
import com.joueurs.api.utils.PaginationCompteResponse;


import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("api/compte")
@CrossOrigin
public class ComptesController {

	private  ICompteService compteService;

	public ComptesController(ICompteService compteService){
	this.compteService = compteService;}
	
	@GetMapping("/all")
	@Operation(summary = "Get ALL Comptes", description = "This endpoint retrieve all comptes")
	public PaginationCompteResponse getAllComptes(
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy){
	 
	return compteService.getAllCompte(pageNo,pageSize,sortBy);
	}
	
	@PostMapping("/add")
	@Operation(summary = "Create compte", description = "This endpoint create a compte") // Documentation Swagger
	public ResponseEntity<CompteDTO> createCompte(@RequestBody CompteCreateDTO compteCreateDto){
		
		return new ResponseEntity<>(compteService.createCompte(compteCreateDto), HttpStatus.CREATED);	
	}
}
