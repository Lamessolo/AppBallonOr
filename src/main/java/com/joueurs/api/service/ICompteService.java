package com.joueurs.api.service;

import com.joueurs.api.dto.CompteCreateDTO;
import com.joueurs.api.dto.CompteDTO;
import com.joueurs.api.utils.PaginationCompteResponse;


public interface ICompteService {

		CompteDTO createCompte(CompteCreateDTO compteCreateDto);
	
	    PaginationCompteResponse getAllCompte(int pageNo,int pageSize,String sortBy);
	
}
