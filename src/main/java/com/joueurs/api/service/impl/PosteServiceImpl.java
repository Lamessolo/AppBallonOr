package com.joueurs.api.service.impl;

import java.util.List;
import java.util.Map;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.joueurs.api.dto.PosteDTO;
import com.joueurs.api.entity.Poste;
import com.joueurs.api.exception.ResourceNotFoundException;
import com.joueurs.api.repository.PosteRepository;
import com.joueurs.api.service.IPosteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PosteServiceImpl implements IPosteService {

	private final PosteRepository posteRepository ;
	private final ModelMapper mapper;
	
	private PosteDTO mapEntityToDTO(Poste poste) {
		return mapper.map(poste, PosteDTO.class);			
	}
	
	public  Poste mapDtoToEntity(PosteDTO posteDto) {
			     
		return mapper.map(posteDto, Poste.class);		
	}
	
	
	@Override
	public PosteDTO createPoste(PosteDTO posteCreateDto) {
		
		// Je transforme le PosteDTO en une nouvelle entité Poste
	       Poste posteNew = mapDtoToEntity(posteCreateDto);
			
			// Je sauve une entité dans la base			 
			posteRepository.save(posteNew);
			
			return mapEntityToDTO(posteNew);
	}

	@Override
	public List<Poste> getAllPoste() {
		return posteRepository.findAll();
	}

	@Override
	public PosteDTO findPosteById(long id) {
	
		Poste posteById = posteRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Poste","id",id));		
		return mapEntityToDTO(posteById);
		
	}

	@Override
	public PosteDTO updatePoste(long id, PosteDTO posteCreateDto) {
		
		return null;
	}

	@Override
	public Map<String, Boolean> deletePoste(long posteId) {
		
		return null;
	}

}
