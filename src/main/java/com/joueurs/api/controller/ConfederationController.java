package com.joueurs.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.dto.ConfederationDTO;
import com.joueurs.api.service.IConfederationService;


import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("api/confederation")
@CrossOrigin
public class ConfederationController {

	private final IConfederationService confederationService;
	
	public ConfederationController(IConfederationService confederationService) {
		this.confederationService = confederationService;
	}
	
	@GetMapping("/all")
	@Operation(summary = "Get Confederations", description = "This endpoint retrieve all Confederations")
	public List<ConfederationDTO> getConfederations(){
		 
		return confederationService.getAllConfederation();
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Get Confederation By Id", description = "This endpoint retrieve Confederation by Id")
	public ResponseEntity<ConfederationDTO> getConfederationById(@PathVariable("id") int confederationId){
		
		return new ResponseEntity<>(confederationService.findConfederationById(confederationId), HttpStatus.OK);
		}
	
}
