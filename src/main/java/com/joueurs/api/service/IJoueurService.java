package com.joueurs.api.service;
import java.util.List;
import java.util.Map;

import com.joueurs.api.dto.JoueurCreateDTO;
import com.joueurs.api.dto.JoueurDTO;
import com.joueurs.api.utils.PaginationResponse;

public interface IJoueurService  {

	JoueurDTO createJoueur(JoueurCreateDTO joueurCreateDto);
	
	PaginationResponse getAllJoueur(int pageNo,int pageSize,String sortBy);
	
	PaginationResponse getVainqueurBallondOr(int pageNo,int pageSize,String sortBy);
	
	PaginationResponse findByPosteId(int pageNo,int pageSize,String sortBy,long byPosteId);
	
	PaginationResponse findByAnnee(int pageNo, int pageSize, String sortBy, String byAnnee);
	
	PaginationResponse findByClassementPosition(int pageNo, int pageSize, String sortBy, int byClassementPosition);
	
	PaginationResponse findByClubId(int pageNo, int pageSize, String sortBy, long byClubId);
	
	PaginationResponse findJoueurByMotClef(int pageNo, int pageSize, String sortBy, String byMotClef);
	
	JoueurDTO findJoueurById (long id);
	
	JoueurDTO updateJoueur(long id,JoueurCreateDTO joueurCreateDto);
	
	Map<String,Boolean> deleteJoueur(long joueurId);
	
	List<JoueurDTO> searchJoueurByPoste(long posteId);

	List<JoueurDTO> searchJoueurByAnnee(String annee);

	PaginationResponse findJoueurByParametres(
		long posteId,
			int classement,
			String anneeRecompense,
			int pageNo, int pageSize, 
			String sortBy);

	
	PaginationResponse findJoueurByPosteAndClassementParametres(long posteId,
			int classement,
			int pageNo, int pageSize, 
			String sortBy);

	JoueurDTO assignedTitreToJoueur(long joueurId, long titreId);

	
	



	

	
	
	
	
	 
	
}
