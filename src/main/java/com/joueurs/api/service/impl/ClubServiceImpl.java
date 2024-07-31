package com.joueurs.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.joueurs.api.exception.ClubNotFoundException;
import com.joueurs.api.repository.ClubRepository;
import com.joueurs.api.service.IClubService;
import com.joueurs.api.utils.PaginationClubResponse;

@Service
public class ClubServiceImpl implements IClubService {

	private  ClubRepository clubRepository;
	private   ModelMapper mapper;
	    
	   public ClubServiceImpl (ClubRepository clubRepository,ModelMapper mapper) {
		   this.clubRepository = clubRepository;
		   this.mapper = mapper;
	   }
	   
	   private  ClubDTO mapEntityToDto(Club club) 
		{		
			return mapper.map(club, ClubDTO.class);
		}
		
		private Club mapDtoToEntity(ClubCreateDTO clubCreateDto) 
		{		
			return mapper.map(clubCreateDto, Club.class);
		}	

	@Override
	public  List<ClubDTO> getAllClub(){		
		
		List<Club> listeDesClubs = clubRepository.findAll();			
		return listeDesClubs.stream()
							.map(this::mapEntityToDto)
							.collect(Collectors.toList());		
	}

	@Override
	public ClubDTO findClubById(long clubId) {	
		Club club= clubRepository
				.findById(clubId)
				.orElseThrow(() -> new ClubNotFoundException("Club", "id", clubId));	
		return mapEntityToDto(club);
		
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
				.map(this::mapEntityToDto)
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
		
		Club club = clubRepository
				.findById(clubId)
				.orElseThrow(() -> new ClubNotFoundException("Club", "id", clubId));
   
		club.setName(clubCreateDto.getName());
		club.setPays(clubCreateDto.getPays());	
					    
		clubRepository.save(club);									
		return mapEntityToDto(club);
														
	}

	@Override
	@Transactional  // Ajoutez cette annotation
	public ClubDTO createClub(ClubCreateDTO clubCreateDto) {
	        Club newClub = new Club();
	        newClub.setName(clubCreateDto.getName());
	        newClub.setPays(clubCreateDto.getPays());
	        
	        clubRepository.save(newClub);
	        
		return mapEntityToDto(newClub);
	}

	@Override
	public Map<String, Boolean> deleteClub(long clubId) {
		Club club = clubRepository
				.findById(clubId)
				.orElseThrow(()-> new ClubNotFoundException("Club", "id", clubId));
		clubRepository.delete(club);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@Override
	public List<ClubDTO> getClubByPays(String pays) {
		
		List<Club> ClubsByPays = clubRepository.findByPays(pays);			
		return ClubsByPays.stream()
							.map(this::mapEntityToDto)
							.collect(Collectors.toList());
	}

	@Override
	public List<ClubDTO> searchClubByName(String term) {
		List<Club> ClubsByName = clubRepository.searchByName(term);			
		return ClubsByName.stream()
							.map(this::mapEntityToDto)
							.collect(Collectors.toList());
	}

}
