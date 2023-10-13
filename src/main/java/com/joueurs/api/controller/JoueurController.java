package com.joueurs.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.utils.ConstanteApp;
import com.joueurs.api.dto.JoueurCreateDTO;
import com.joueurs.api.dto.JoueurDTO;
import com.joueurs.api.exception.JoueurNotFoundException;
import com.joueurs.api.service.IJoueurService;
import com.joueurs.api.utils.PaginationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/joueur")
@CrossOrigin
public class JoueurController {

private final IJoueurService joueurService;
	
	
	@GetMapping("/all")
	public PaginationResponse getAllJoueurs(
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy){
	 
	return joueurService.getAllJoueur(pageNo,pageSize,sortBy);
	}

		
	@GetMapping("/{id}")
	public ResponseEntity<JoueurDTO> getJoueurById(@PathVariable("id") long joueurId){
		try {
			JoueurDTO joueur = joueurService.findJoueurById(joueurId);
			return new ResponseEntity<>(joueur, HttpStatus.OK);
		}catch(JoueurNotFoundException e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		}
	
	@PostMapping("/add")
	public ResponseEntity<JoueurDTO> createJoueur(@RequestBody JoueurCreateDTO joueurCreateDto){
		try {
			JoueurDTO newJoueur = joueurService.createJoueur(joueurCreateDto);
			return new ResponseEntity<>(newJoueur, HttpStatus.CREATED);	
		} catch(Exception e) {
			
			  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
	
	}
		
	@PutMapping("{id}")
	public ResponseEntity<JoueurDTO> updateJoueur(@PathVariable("id") long joueurId,@RequestBody JoueurCreateDTO joueurCreateDto)
	{
		try {
			JoueurDTO updatedJoueur =  joueurService.updateJoueur(joueurId,joueurCreateDto);		 
			return new ResponseEntity<>(updatedJoueur,HttpStatus.OK);
		}catch(JoueurNotFoundException e) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	}
	
	@PutMapping("{joueurId}/titre/{titreId}")
	public ResponseEntity<JoueurDTO> assignedTitreToJoueur (
			@PathVariable("joueurId")long joueurId, 
			@PathVariable("titreId")int titreId){
		try {
			JoueurDTO assignedTitreToJoueur = joueurService.assignedTitreToJoueur(joueurId,titreId);
			return new ResponseEntity<>(assignedTitreToJoueur,HttpStatus.OK);
		}catch(JoueurNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String,Boolean>> deleteJoueur(@PathVariable("id") long joueurId){	
		try {return new ResponseEntity<>(joueurService.deleteJoueur(joueurId), HttpStatus.OK);}
		catch(JoueurNotFoundException e ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
		
	@GetMapping("/search")
	public PaginationResponse getJoueurByMotClef(
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy,
			@RequestParam(value="byMotClef",required=false) String byMotClef
			) {
		return joueurService.findJoueurByMotClef(pageNo,pageSize,sortBy,byMotClef);
	}

	@GetMapping("/poste")
	public PaginationResponse getJoueursByPoste(
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy,
		@RequestParam(value="byPosteId",required=false) int byPosteId
		){
	return joueurService.findByPosteId(pageNo,pageSize,sortBy,byPosteId);
	}
	
	@GetMapping("/annee")
	public PaginationResponse getJoueursByAnnee(
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy,
		@RequestParam(value="byAnnee",required=false) String byAnnee
		){
	 
	return joueurService.findByAnnee(pageNo,pageSize,sortBy,byAnnee);
	}
	
	@GetMapping("/position")
	public PaginationResponse getJoueursByClassementPosition(
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy,
		@RequestParam(value="byClassement",required=false) int byClassementPosition
		){
	 
	return joueurService.findByClassementPosition(pageNo,pageSize,sortBy,byClassementPosition);
	}
	
	@GetMapping("/club")
	public PaginationResponse getJoueurByClubId(
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy,
			@RequestParam(value="byClubId",required=false) int byClubId
			) {
		return joueurService.findByClubId(pageNo,pageSize,sortBy,byClubId);
	}
	
	@GetMapping("/searchByParametres/poste/{posteId}/classement/{classement}/annee/{anneeRecompense}")
	public PaginationResponse searchJoueurByParametres(
			@PathVariable("posteId")int posteId,
			@PathVariable("classement")int classement,
			@PathVariable("anneeRecompense")String anneeRecompense,
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy)
	{		   		
		    return joueurService.findJoueurByParametres(posteId,classement,anneeRecompense,pageNo,pageSize,sortBy);	
	}
	
	
	@GetMapping("/poste/{posteId}/classement/{classement}")
	public PaginationResponse getJoueurByParametres(
			@PathVariable("posteId")int posteId,
			@PathVariable("classement")int classement,
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy)
			{	
		
		    return joueurService.findJoueurByPosteAndClassementParametres(posteId,classement,pageNo,pageSize,sortBy);	
	}
	
	
	
	
	
	
}
