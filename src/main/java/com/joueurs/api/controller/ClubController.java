package com.joueurs.api.controller;

import java.util.List;
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

import com.joueurs.api.dto.ClubCreateDTO;
import com.joueurs.api.dto.ClubDTO;
import com.joueurs.api.service.IClubService;
import com.joueurs.api.utils.ConstanteApp;
import com.joueurs.api.utils.PaginationClubResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/club")
@CrossOrigin
public class ClubController {

	private final IClubService clubService;
	
	@GetMapping("/all")
	public List<ClubDTO> getAllClubs()
	{		 
		return clubService.getAllClub();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClubDTO> getClubById(@PathVariable("id") int clubId){
		return new ResponseEntity<>(clubService.findClubById(clubId), HttpStatus.OK);
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
		return new ResponseEntity<>(clubService.updateClub(clubId,clubCreateDto),HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<ClubDTO> createClub(@RequestBody ClubCreateDTO clubCreateDto) throws Exception{
		if(clubCreateDto == null) throw new IllegalAccessException("les données entrées sont incorrects");
		return new ResponseEntity<>(clubService.createClub(clubCreateDto), HttpStatus.CREATED);	
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Delete Poste By Id", description = "This endpoint delete poste by Id")
	public ResponseEntity<Map<String,Boolean>> deleteClub(@PathVariable("id") int clubId){	
			return new ResponseEntity<>(clubService.deleteClub(clubId), HttpStatus.OK);
	}	
	
}

