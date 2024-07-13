package com.joueurs.api.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
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

import com.joueurs.api.service.IJoueurService;
import com.joueurs.api.utils.PaginationResponse;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@RequestMapping("api/joueur")
@CrossOrigin
public class JoueurController {

private  IJoueurService joueurService;

	
	public JoueurController(IJoueurService joueurService) {
	this.joueurService = joueurService;
}


	@GetMapping("/all")
	@Operation(summary = "Get ALL Joueurs", description = "This endpoint retrieve all joueur")
	public PaginationResponse getAllJoueurs(
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy){
	 
	return joueurService.getAllJoueur(pageNo,pageSize,sortBy);
	}
	
		
	@GetMapping("/{id}")
	@Operation(summary = "Get Joueur By Id", description = "This endpoint retrieve joueur by Id")
	public ResponseEntity<JoueurDTO> getJoueurById(@PathVariable("id") int joueurId){
		
		return new ResponseEntity<>(joueurService.findJoueurById(joueurId), HttpStatus.OK);
		}
	
	@PostMapping("/add")
	@Operation(summary = "Create Joueur", description = "This endpoint create a Joueur") // Documentation Swagger
	public ResponseEntity<JoueurDTO> createJoueur(@RequestBody JoueurCreateDTO joueurCreateDto){
		return new ResponseEntity<>(joueurService.createJoueur(joueurCreateDto), HttpStatus.CREATED);	
	}
		
	@PutMapping("{id}")
	@Operation(summary = "Update Joueur", description = "This endpoint update a joueur")
	public ResponseEntity<JoueurDTO> updateJoueur(@PathVariable("id") int joueurId,@RequestBody JoueurCreateDTO joueurCreateDto)
	{				 
			return new ResponseEntity<>(joueurService.updateJoueur(joueurId,joueurCreateDto),HttpStatus.OK);		
	}
		
	@DeleteMapping("{id}")
	@Operation(summary = "Delete Joueur By Id", description = "This endpoint delete joueur by Id")
	public ResponseEntity<Map<String,Boolean>> deleteJoueur(@PathVariable("id") int joueurId){	
			return new ResponseEntity<>(joueurService.deleteJoueur(joueurId), HttpStatus.OK);
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

	
	@GetMapping("/poste/{id}")
	public PaginationResponse getJoueursByPoste(
			@PathVariable("id")int posteId,
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy	
		){
	return joueurService.findByPosteId(posteId,pageNo,pageSize,sortBy);
	}
	
	
	@GetMapping("/rate/{rateNbr}")
	public PaginationResponse getJoueursByRate(
			@PathVariable("rateNbr")int rateNbr,
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy	
		){
	return joueurService.findByRate(rateNbr,pageNo,pageSize,sortBy);
	}
	
	@GetMapping("/selection/{selectionId}")
	public PaginationResponse getJoueursBySelection(
			@PathVariable("selectionId")int selectionId,
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy	
		){
	return joueurService.findBySelection(selectionId,pageNo,pageSize,sortBy);
	}
	
	@GetMapping("/annee/{year}")
	public PaginationResponse getJoueursByAnnee(
			@PathVariable("year")String year,
		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
		@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
		@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy
		){
	 
	return joueurService.findByAnnee(year,pageNo,pageSize,sortBy);
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
	
	
	@GetMapping("/confederation/{confederationId}")
	public PaginationResponse getJoueurByConfederation(
			@PathVariable("confederationId")int byConfederationId,
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy)
	{
		return joueurService.findByConfederation(pageNo,pageSize,sortBy,byConfederationId);
	}
	
	@GetMapping("/pays/{paysName}")
	@Operation(summary = "Get Joueurs by paysName", description = "This endpoint retrieves all players from the same country.")
	public PaginationResponse getJoueurByClubOfPays(
			@PathVariable("paysName")String paysName,
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy
			) {
		return joueurService.findByPaysName(paysName,pageNo,pageSize,sortBy);
	}
	
	 @GetMapping("/mois-anniversaire")
	 @Operation(summary = "This is birthday players", description = "This endpoint retrieves all players.")
	public PaginationResponse getJoueursByTodayBirthday(
				@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
				@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
				@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy	
				) {
			return joueurService.findJoueursByTodayBirthday(pageNo,pageSize,sortBy);
		}
	 
	@GetMapping("/datenaissance")
	@Operation(summary = "This is birthday players", description = "This endpoint retrieves all players.")
    public PaginationResponse getJoueursByDateDeNaissance(
	    		@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
				@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
				@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy,
	    		@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
	        return joueurService.findByDateNaissance(date,pageNo,pageSize,sortBy);
	    }
	
	
}
