package com.joueurs.api.service;

import com.joueurs.api.dto.ClubCreateDTO;
import com.joueurs.api.dto.ClubDTO;
import com.joueurs.api.utils.PaginationClubResponse;



public interface IClubService  {

	PaginationClubResponse getAllClub (int pageNo,int pageSize,String sortBy);

	ClubDTO findClubById(long clubId);
	
	PaginationClubResponse findClubAllByPays(String pays,int pageNo,int pageSize,String sortBy);

	ClubDTO updateClub(long clubId, ClubCreateDTO clubCreateDto);

	ClubDTO createClub(ClubCreateDTO clubCreateDto);
}
