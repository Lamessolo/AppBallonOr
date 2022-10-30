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

import com.joueurs.api.utils.ConstanteApp;

import com.joueurs.api.dto.JoueurCreateDTO;
import com.joueurs.api.dto.JoueurDTO;
import com.joueurs.api.service.IJoueurService;
import com.joueurs.api.utils.PaginationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/joueur")
@CrossOrigin
public class JoueurController {

private final IJoueurService joueurService;
	
	
	@GetMapping("/all")
	public PaginationResponse getAllJoueurs(
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMEBR,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy){
	 
	return joueurService.getAllJoueur(pageNo,pageSize,sortBy);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JoueurDTO> getJoueurById(@PathVariable("id") long joueurId){
		JoueurDTO joueur = joueurService.findJoueurById(joueurId);
		return new ResponseEntity<>(joueur, HttpStatus.OK);
		}
	
	@PostMapping("/add")
	public ResponseEntity<JoueurDTO> createAdherent(@RequestBody JoueurCreateDTO joueurCreateDto){		
		JoueurDTO newJoueur = joueurService.createJoueur(joueurCreateDto);
		return new ResponseEntity<>(newJoueur, HttpStatus.CREATED);	
	}
		
	@PutMapping("update/{id}")
	public ResponseEntity<JoueurDTO> updateAdherent(@PathVariable("id") long joueurId,@RequestBody JoueurCreateDTO joueurCreateDto){
		JoueurDTO updatedJoueur =  joueurService.updateJoueur(joueurId,joueurCreateDto);		 
		return new ResponseEntity<>(updatedJoueur,HttpStatus.OK);
		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteJoueur(@PathVariable("id") long joueurId){		
		return new ResponseEntity<>(joueurService.deleteJoueur(joueurId), HttpStatus.OK);
	}
	
	
}
