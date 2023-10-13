package com.joueurs.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
		List<ClubDTO> content = listeDesClubs.stream().map(this::mapEntityToDTO).collect(Collectors.toList());			
		return content;	
		
	}

	@Override
	public ClubDTO findClubById(long clubId) {
		
		Optional<Club> entityClubOptional = clubRepository.findById(clubId);
		if (entityClubOptional.isPresent())
		{  Club club = entityClubOptional.get();
		return mapEntityToDTO(club);
		}else {
			throw new IllegalArgumentException("L'id du club est incorrect");
		}	
		
	}

	@Override
	public PaginationClubResponse findClubAllByPays(String pays, int pageNo, int pageSize, String sortBy) {
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		
		if(pays == "null") {	
				System.out.println("pays is null");
		}
		Page<Club> listeDesClubByCountry = clubRepository.findByPays(pays,pageable);
		List<Club> clubsByCountry = listeDesClubByCountry.getContent();
		List<ClubDTO> ClubByCountry = clubsByCountry.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		
		PaginationClubResponse pageClubsResponse = new PaginationClubResponse();
		pageClubsResponse.setContent(ClubByCountry);
		pageClubsResponse.setPageNo(listeDesClubByCountry.getNumber());
		pageClubsResponse.setPageSize(listeDesClubByCountry.getSize());
		pageClubsResponse.setTotalElements(listeDesClubByCountry.getTotalElements());
		pageClubsResponse.setTotalPages(listeDesClubByCountry.getTotalPages());
		pageClubsResponse.setLast(listeDesClubByCountry.isLast());
		return pageClubsResponse;	
	
	}

	@Override
	public ClubDTO updateClub(long clubId, ClubCreateDTO clubCreateDto) {
		if(clubId == 0 || clubCreateDto == null) throw new IllegalArgumentException("L'un de vos parametres est null. (clubId, clubDto");
		
		// Recuperer l'entite Club by Id
		Optional<Club> clubEntiteOptional = clubRepository.findById(clubId);
					
// Vérifiez si les objets Optionals contiennent des valeurs avant d'extraire les entités
				if (clubEntiteOptional.isPresent())
				{
					    Club clubToUpdate = clubEntiteOptional.get();				   
					    clubToUpdate.setName(clubCreateDto.getName());
					    clubToUpdate.setPays(clubCreateDto.getPays());	
					    
					    clubRepository.save(clubToUpdate);
											
						return mapEntityToDTO(clubToUpdate);
				}else{
					// Gérez le cas où l'une des entités n'a pas été trouvée
				    // Vous pouvez générer une exception, renvoyer une erreur, ou prendre d'autres mesures appropriées.
				  throw new IllegalArgumentException("Une erreur est survenue lors de la modification du Club");
				}
														
	}

	@Override
	public ClubDTO createClub(ClubCreateDTO clubCreateDto) {
	        Club newClubSaved = new Club();
	        newClubSaved.setName(clubCreateDto.getName());
	        newClubSaved.setPays(clubCreateDto.getPays());
	        
	        clubRepository.save(newClubSaved);
	        
		return mapEntityToDTO(newClubSaved);
	}

}
