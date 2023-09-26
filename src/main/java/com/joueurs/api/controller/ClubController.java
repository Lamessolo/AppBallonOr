package com.joueurs.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.dto.ClubCreateDTO;
import com.joueurs.api.dto.ClubDTO;
import com.joueurs.api.dto.JoueurCreateDTO;
import com.joueurs.api.dto.JoueurDTO;
import com.joueurs.api.exception.ClubNotFoundException;
import com.joueurs.api.service.IClubService;
import com.joueurs.api.utils.ConstanteApp;
import com.joueurs.api.utils.PaginationClubResponse;


import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/club")
@CrossOrigin
public class ClubController {

	private final IClubService clubService;
	
	@GetMapping("/all")
	public PaginationClubResponse getAllClubs(
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy){
		 
		return clubService.getAllClub(pageNo,pageSize,sortBy);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClubDTO> getClubById(@PathVariable("id") long clubId){
		if(clubId < 0){
			throw new ClubNotFoundException("Club id not found " + clubId);
		}
		ClubDTO club = clubService.findClubById(clubId);
		return new ResponseEntity<>(club, HttpStatus.OK);
		}
	
	@GetMapping("country/{pays}")
	public PaginationClubResponse getClubByCountry(@PathVariable("pays")String pays,
			@RequestParam(value="pageNo",defaultValue= ConstanteApp.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			@RequestParam(value="pageSize",defaultValue= ConstanteApp.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue= ConstanteApp.DEFAULT_SORT_BY,required=false) String sortBy)
	{ 
		return clubService.findClubAllByPays(pays,pageNo,pageSize,sortBy);
		}
		
	@PutMapping("{id}")
	public ResponseEntity<ClubDTO> updateClub(@PathVariable("id") int clubId,@RequestBody ClubCreateDTO clubCreateDto){
		ClubDTO updatedClub =  clubService.updateClub(clubId,clubCreateDto);		 
		return new ResponseEntity<>(updatedClub,HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<ClubDTO> createClub(@RequestBody ClubCreateDTO clubCreateDto) throws Exception{
		if(clubCreateDto == null) throw new IllegalAccessException("les données entrées sont incorrects");
		ClubDTO newClub = clubService.createClub(clubCreateDto);
		return new ResponseEntity<>(newClub, HttpStatus.CREATED);	
	}
	
}

