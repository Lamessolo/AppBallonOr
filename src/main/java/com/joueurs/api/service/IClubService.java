package com.joueurs.api.service;

import java.util.List;

import com.joueurs.api.dto.ClubCreateDTO;
import com.joueurs.api.dto.ClubDTO;
import com.joueurs.api.entity.Club;
import com.joueurs.api.utils.PaginationClubResponse;



public interface IClubService  {

	List<ClubDTO> getAllClub ();

	ClubDTO findClubById(long clubId);
	
	PaginationClubResponse findClubAllByPays(String pays,int pageNo,int pageSize,String sortBy);

	ClubDTO updateClub(long clubId, ClubCreateDTO clubCreateDto);

	ClubDTO createClub(ClubCreateDTO clubCreateDto);
}
