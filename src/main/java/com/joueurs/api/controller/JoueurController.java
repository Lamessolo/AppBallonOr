package com.joueurs.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.entity.Joueur;
import com.joueurs.api.service.JoueurService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/joueur")
@CrossOrigin
public class JoueurController {

private final JoueurService joueurService;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Joueur>> getAllJoueursWithPaginationAndSort(@RequestParam int page, @RequestParam int size,@RequestParam String field){
		List<Joueur> joueurs = joueurService.getAllJoueurs(page,size,field);
		return new ResponseEntity<>(joueurs, HttpStatus.OK);
		}
}
