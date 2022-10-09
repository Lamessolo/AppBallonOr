package com.joueurs.api.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.joueurs.api.entity.Joueur;
import com.joueurs.api.repository.JoueurRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoueurService {

	private final JoueurRepository joueurRepo;
	
	
	public List<Joueur> getAllJoueurs (int page, int size, String field){
		
		PageRequest pegeable =PageRequest.of(page, size).withSort(Sort.by(field));
		Page<Joueur> listeDesJoueurs = joueurRepo.findAll(pegeable);
		
		List<Joueur> joueurs = listeDesJoueurs.getContent();
		
		return joueurs;
		
	}
	
	
}
