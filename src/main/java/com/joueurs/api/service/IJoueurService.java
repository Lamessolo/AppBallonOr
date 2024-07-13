package com.joueurs.api.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.joueurs.api.dto.JoueurCreateDTO;
import com.joueurs.api.dto.JoueurDTO;
import com.joueurs.api.entity.Joueur;
import com.joueurs.api.utils.PaginationResponse;

public interface IJoueurService  {

	JoueurDTO createJoueur(JoueurCreateDTO joueurCreateDto);
	
	PaginationResponse getAllJoueur(int pageNo,int pageSize,String sortBy);
	
	PaginationResponse getVainqueurBallondOr(int pageNo,int pageSize,String sortBy);
	
	PaginationResponse findByPosteId(long byPosteId,int pageNo,int pageSize,String sortBy);
	
	PaginationResponse findByRate(long byRate,int pageNo,int pageSize,String sortBy);
	
	PaginationResponse findByConfederation(int pageNo,int pageSize,String sortBy,long byConfederation);
	
	PaginationResponse findByAnnee(String year,int pageNo, int pageSize, String sortBy);
	
	PaginationResponse findBySelection(int selectionId,int pageNo, int pageSize, String sortBy);
	
	PaginationResponse findByClassementPosition(int pageNo, int pageSize, String sortBy, int byClassementPosition);
	
	PaginationResponse findByClubId(int pageNo, int pageSize, String sortBy, long byClubId);
	
	PaginationResponse findJoueurByMotClef(int pageNo, int pageSize, String sortBy, String byMotClef);
	
	JoueurDTO findJoueurById (long id);
	
	JoueurDTO updateJoueur(long id,JoueurCreateDTO joueurCreateDto);
	
	Map<String,Boolean> deleteJoueur(long joueurId);
	

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

	PaginationResponse findByPaysName(String paysName, int pageNo, int pageSize, String sortBy);

	PaginationResponse findJoueursByTodayBirthday(int pageNo,int pageSize,String sortBy);
	
	PaginationResponse findByDateNaissance(Date dateNaissance,int pageNo,int pageSize,String sortBy);
	



	

	
	
	
	
	 
	
}
