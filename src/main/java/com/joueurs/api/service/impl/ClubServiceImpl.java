package com.joueurs.api.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joueurs.api.dto.ClubCreateDTO;
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
	
	public  Club mapDtoToEntity(ClubCreateDTO clubCreateDto) {
	   
		return mapper.map(clubCreateDto, Club.class);		
	}
	

	@Override
	public  List<ClubDTO> getAllClub(){		
		
		List<Club> listeDesClubs = clubRepository.findAll();			
		return listeDesClubs.stream()
							.map(this::mapEntityToDTO)
							.collect(Collectors.toList());	
		
	}

	@Override
	public ClubDTO findClubById(long clubId) {
		
		Optional<Club> entityClubOptional = clubRepository.findById(clubId);
		Club club = entityClubOptional.orElseThrow(() -> new NoSuchElementException("Club not found"));		
		return mapEntityToDTO(club);
		
	}

	@Override
	public PaginationClubResponse findClubAllByPays(String pays, int pageNo, int pageSize, String sortBy) {
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		
		if(pays == "null") {	
				System.out.println("pays is null");
		}
		Page<Club> listeDesClubByCountry = clubRepository.findByPays(pays,pageable);
		List<ClubDTO> Clubs = listeDesClubByCountry.getContent()
				.stream()
				.map(this::mapEntityToDTO)
				.collect(Collectors.toList());
		
		PaginationClubResponse response = new PaginationClubResponse();
		response.setContent(Clubs);
		response.setPageNo(listeDesClubByCountry.getNumber());
		response.setPageSize(listeDesClubByCountry.getSize());
		response.setTotalElements(listeDesClubByCountry.getTotalElements());
		response.setTotalPages(listeDesClubByCountry.getTotalPages());
		response.setLast(listeDesClubByCountry.isLast());
		return response;	
	
	}

	@Override
	@Transactional  // Ajoutez cette annotation
	public ClubDTO updateClub(long clubId, ClubCreateDTO clubCreateDto) {
		if(clubId == 0 || clubCreateDto == null) throw new IllegalArgumentException("Invalid parameters (clubId, clubDto)");
		
		
		Optional<Club> clubEntiteOptional = clubRepository.findById(clubId);
		Club club = clubEntiteOptional.orElseThrow(() -> new NoSuchElementException("Club not found"));	
		  			   
		club.setName(clubCreateDto.getName());
		club.setPays(clubCreateDto.getPays());	
					    
		clubRepository.save(club);
										
		return mapEntityToDTO(club);
														
	}

	@Override
	@Transactional  // Ajoutez cette annotation
	public ClubDTO createClub(ClubCreateDTO clubCreateDto) {
	        Club newClub = new Club();
	        newClub.setName(clubCreateDto.getName());
	        newClub.setPays(clubCreateDto.getPays());
	        
	        clubRepository.save(newClub);
	        
		return mapEntityToDTO(newClub);
	}

}
