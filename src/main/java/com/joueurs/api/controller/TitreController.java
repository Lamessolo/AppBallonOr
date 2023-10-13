package com.joueurs.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.joueurs.api.dto.TitreCreateDTO;
import com.joueurs.api.dto.TitreDTO;
import com.joueurs.api.exception.TitreNotFoundException;
import com.joueurs.api.service.ITitreService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/titre")
@CrossOrigin
public class TitreController {


	private final ITitreService titreService;
	
	
	@GetMapping("/all")
	public List<TitreDTO> getAllTitre(){
		return titreService.getAllTitre();	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TitreDTO> getTitreById(@PathVariable("id") Integer titreId){
		try {
			TitreDTO titre = titreService.findTitreById(titreId);
			return new ResponseEntity<>(titre, HttpStatus.OK);
		}catch(TitreNotFoundException e) {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
		
		}
	
	@PostMapping("/add")
	public ResponseEntity<TitreDTO> createTitre(@RequestBody TitreCreateDTO titreCreateDto){
		try {
			TitreDTO newTitre = titreService.createTitre(titreCreateDto);
			return new ResponseEntity<>(newTitre, HttpStatus.CREATED);	
		}catch(Exception e) {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
		}
		
	}




