package com.joueurs.api.service;


import com.joueurs.api.utils.PaginationClubResponse;


public interface IClubService  {

	PaginationClubResponse getAllClub (int pageNo,int pageSize,String sortBy);
}
