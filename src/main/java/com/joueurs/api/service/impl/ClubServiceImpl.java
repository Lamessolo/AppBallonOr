package com.joueurs.api.service.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.joueurs.api.dto.ClubDTO;
import com.joueurs.api.entity.Club;
import com.joueurs.api.repository.ClubRepository;
import com.joueurs.api.service.IClubService;
import com.joueurs.api.utils.PaginationClubResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements IClubService {

	private final ClubRepository clubRepository;
	private final ModelMapper mapper;
	
     private ClubDTO mapEntityToDTO(Club club) {
		
		return mapper.map(club, ClubDTO.class);			
	}
	
	public  Club mapDtoToEntity(ClubDTO clubDto) {
	   
		return mapper.map(clubDto, Club.class);		
	}
	

	@Override
	public PaginationClubResponse getAllClub(int pageNo, int pageSize, String sortBy) {
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Club> listeDesClubs = clubRepository.findAll(pageable);
		List<Club> clubs = listeDesClubs.getContent();
		List<ClubDTO> content = clubs.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		
		PaginationClubResponse pageClubsResponse = new PaginationClubResponse();
		pageClubsResponse.setContent(content);
		pageClubsResponse.setPageNo(listeDesClubs.getNumber());
		pageClubsResponse.setPageSize(listeDesClubs.getSize());
		pageClubsResponse.setTotalElements(listeDesClubs.getTotalElements());
		pageClubsResponse.setTotalPages(listeDesClubs.getTotalPages());
		pageClubsResponse.setLast(listeDesClubs.isLast());
		return pageClubsResponse;	
		
	}

}
