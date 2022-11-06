package com.joueurs.api.service;
import java.util.List;
import java.util.Map;

import com.joueurs.api.dto.JoueurCreateDTO;
import com.joueurs.api.dto.JoueurDTO;
import com.joueurs.api.utils.PaginationResponse;

public interface IJoueurService  {

	JoueurDTO createJoueur(JoueurCreateDTO joueurCreateDto);
	
	PaginationResponse getAllJoueur(int pageNo,int pageSize,String sortBy);
	
	JoueurDTO findJoueurById (long id);
	
	JoueurDTO updateJoueur(long id,JoueurCreateDTO joueurCreateDto);
	
	Map<String,Boolean> deleteJoueur(long joueurId);
	
	List<JoueurDTO> SearchJoueurByNameOrPrenom(String name);
	
}
