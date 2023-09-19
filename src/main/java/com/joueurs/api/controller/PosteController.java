package com.joueurs.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.dto.PosteDTO;
import com.joueurs.api.entity.Poste;

import com.joueurs.api.service.IPosteService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/poste")
@RequiredArgsConstructor
@CrossOrigin
public class PosteController {
	
	private final IPosteService posteService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Poste>> getAllPostes(){
		
		List<Poste> listedespostes = posteService.getAllPoste();
	return new ResponseEntity<>(listedespostes, HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PosteDTO> getPosteById(@PathVariable("id")long posteId){
		PosteDTO poste = posteService.findPosteById(posteId);
	return new ResponseEntity<>(poste, HttpStatus.OK); 
	}

}
