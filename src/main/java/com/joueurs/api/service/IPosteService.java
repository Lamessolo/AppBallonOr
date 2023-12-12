package com.joueurs.api.service;

import java.util.List;
import java.util.Map;

import com.joueurs.api.dto.PosteCreateDTO;
import com.joueurs.api.dto.PosteDTO;
import com.joueurs.api.entity.Poste;



public interface IPosteService {

    PosteDTO createPoste(PosteDTO posteCreateDto);
	
	List<Poste> getAllPoste();
	
	PosteDTO findPosteById (long id);
	
	Map<String,Boolean> deletePoste(long posteId);

	PosteDTO updatePoste(long posteId, PosteCreateDTO posteCreateDto);
	
}
