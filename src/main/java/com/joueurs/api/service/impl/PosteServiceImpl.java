package com.joueurs.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.joueurs.api.dto.PosteCreateDTO;
import com.joueurs.api.dto.PosteDTO;
import com.joueurs.api.entity.Poste;
import com.joueurs.api.exception.PosteNotFoundException;
import com.joueurs.api.repository.PosteRepository;
import com.joueurs.api.service.IPosteService;

@Service
public class PosteServiceImpl implements IPosteService {

	private PosteRepository posteRepository ;
	private ModelMapper mapper;
	
	public PosteServiceImpl(PosteRepository posteRepository,ModelMapper mapper) {	
		this.posteRepository = posteRepository;
		this.mapper = mapper;
	}

	private PosteDTO mapEntityToDto(Poste poste) 
	{		
		return mapper.map(poste, PosteDTO.class);
	}
	
	private Poste mapDtoToEntity(PosteDTO posteDto) 
	{		
		return mapper.map(posteDto, Poste.class);
	}
	
	@Override
	public PosteDTO createPoste(PosteDTO posteCreateDto) {	
		// Je transforme le PosteDTO en une nouvelle entité Poste
	       Poste posteNew = mapDtoToEntity(posteCreateDto);
			// Je sauve une entité dans la base			 
			posteRepository.save(posteNew);
			return mapEntityToDto(posteNew);
	}

	@Override
	public List<Poste> getAllPoste() {
		return posteRepository.findAll();
	}

	@Override
	public PosteDTO findPosteById(long id) {
    Poste poste = posteRepository
    		.findById(id)
    		.orElseThrow(()-> new PosteNotFoundException("Poste", "id", id));
		return mapEntityToDto(poste);	
	}

	@Override
	public PosteDTO updatePoste(long id, PosteCreateDTO posteCreateDto) {
		 Poste poste = posteRepository
		    		.findById(id)
		    		.orElseThrow(()-> new PosteNotFoundException("Poste", "id", id)); 
		 poste.setDescription(posteCreateDto.getDescription());
		 poste.setName(posteCreateDto.getName());
		 posteRepository.save(poste);
		 return mapEntityToDto(poste);	
	}

	@Override
	public Map<String, Boolean> deletePoste(long posteId) {	
		Poste poste = posteRepository
				.findById(posteId)
				.orElseThrow(()-> new PosteNotFoundException("Poste", "id", posteId));
		posteRepository.delete(poste);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


}
