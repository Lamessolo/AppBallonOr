package com.joueurs.api.service;

import java.util.List;

import com.joueurs.api.dto.TitreCreateDTO;
import com.joueurs.api.dto.TitreDTO;


public interface ITitreService {

	TitreDTO createTitre(TitreCreateDTO titreCreateDto);
	
	List<TitreDTO> getAllTitre();
	
	TitreDTO findTitreById(long titreId);
}
