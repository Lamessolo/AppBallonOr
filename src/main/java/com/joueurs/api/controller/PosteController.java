package com.joueurs.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.dto.PosteCreateDTO;
import com.joueurs.api.dto.PosteDTO;
import com.joueurs.api.entity.Poste;

import com.joueurs.api.service.IPosteService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/poste")
@RequiredArgsConstructor
@CrossOrigin
public class PosteController {
	
	private final IPosteService posteService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Poste>> getAllPostes(){
	return new ResponseEntity<>(posteService.getAllPoste(), HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PosteDTO> getPosteById(@PathVariable("id")int posteId){
	return new ResponseEntity<>(posteService.findPosteById(posteId), HttpStatus.OK); 
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Delete Poste By Id", description = "This endpoint delete poste by Id")
	public ResponseEntity<Map<String,Boolean>> deletePoste(@PathVariable("id") int posteId){	
			return new ResponseEntity<>(posteService.deletePoste(posteId), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Update Poste", description = "This endpoint update a poste")
	public ResponseEntity<PosteDTO> updatePoste(@PathVariable("id") long posteId,@RequestBody PosteCreateDTO posteCreateDto)
	{				 
			return new ResponseEntity<>(posteService.updatePoste(posteId,posteCreateDto),HttpStatus.OK);		
	}
}
