package com.joueurs.api.service.Impl;
import com.joueurs.api.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.joueurs.api.dto.JoueurCreateDTO;
import com.joueurs.api.dto.JoueurDTO;
import com.joueurs.api.entity.Club;
import com.joueurs.api.entity.Joueur;
import com.joueurs.api.entity.Poste;
import com.joueurs.api.entity.Selection;
import com.joueurs.api.repository.ClubRepository;
import com.joueurs.api.repository.JoueurRepository;
import com.joueurs.api.repository.PosteRepository;
import com.joueurs.api.repository.SelectionRepository;
import com.joueurs.api.service.IJoueurService;
import com.joueurs.api.utils.PaginationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoueurServiceImpl implements IJoueurService {

	private final JoueurRepository joueurRepository;
	private final PosteRepository posteRepository ;
	private final SelectionRepository selectionRepository;
	private final ClubRepository clubRepository;
	
	private final ModelMapper mapper;
	
	private JoueurDTO mapEntityToDTO(Joueur joueur) {
		
		JoueurDTO joueurDto = mapper.map(joueur, JoueurDTO.class);
	
		return joueurDto;			
	}
	
	public  Joueur mapDtoToEntity(JoueurCreateDTO joueurCreateDto) {
	
		Joueur newJoueur = mapper.map(joueurCreateDto, Joueur.class);	
	     
		return newJoueur;		
	}
	
	@Override
	public JoueurDTO createJoueur(JoueurCreateDTO joueurCreateDto) {
		// Je transforme le JoueurDTO en une nouvelle entité Joueur
		       Joueur joueurNew = mapDtoToEntity(joueurCreateDto);
				Poste posteNew = posteRepository.findById(joueurCreateDto.getPoste())
						.orElseThrow(()->  new ResourceNotFoundException("Poste", "id", joueurCreateDto.getPoste()));
						
				Selection selectionNew = selectionRepository.findById(joueurCreateDto.getSelection())
						.orElseThrow(()-> new ResourceNotFoundException("Selection","id",joueurCreateDto.getSelection()));
				Club clubNew = clubRepository.findById(joueurCreateDto.getClub())
						.orElseThrow(()-> new ResourceNotFoundException("Club","id",joueurCreateDto.getClub()));
				
				joueurNew.setPoste(posteNew);
				joueurNew.setSelection(selectionNew);
				joueurNew.setClub(clubNew);
				
				// Je sauve une entité dans la base			 
				joueurRepository.save(joueurNew);
				
				return mapEntityToDTO(joueurNew);
	}

	@Override
	public PaginationResponse getAllJoueur(int pageNo,int pageSize,String sortBy) {
				
			PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
			Page<Joueur> listeDesJoueurs = joueurRepository.findAll(pageable);
			List<Joueur> joueurs = listeDesJoueurs.getContent();
			List<JoueurDTO> content = listeDesJoueurs.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
			
			PaginationResponse pageJoueursResponse = new PaginationResponse();
			pageJoueursResponse.setContent(content);
			pageJoueursResponse.setPageNo(listeDesJoueurs.getNumber());
			pageJoueursResponse.setPageSize(listeDesJoueurs.getSize());
			pageJoueursResponse.setTotalElements(listeDesJoueurs.getTotalElements());
			pageJoueursResponse.setTotalPages(listeDesJoueurs.getTotalPages());
			pageJoueursResponse.setLast(listeDesJoueurs.isLast());
			return pageJoueursResponse;	
		
	}

	@Override
	public JoueurDTO findJoueurById(long joueurId) {	
			Joueur entityJoueur = joueurRepository.findById(joueurId)
					.orElseThrow(()-> new ResourceNotFoundException("Joueur","id",joueurId));		
			return mapEntityToDTO(entityJoueur);
		
	}

	@Override
	public JoueurDTO updateJoueur(long joueurId, JoueurCreateDTO joueurCreateDto) {
		
			// Recuperer l'entite Joueur by Id
			Joueur joueurEntite = joueurRepository.findById(joueurId)
					.orElseThrow(()-> new ResourceNotFoundException("Joueur", "id", joueurId));
			
			Poste posteDuJoueur = posteRepository.findById(joueurCreateDto.getPoste())
					.orElseThrow(()-> new ResourceNotFoundException("Poste", "id", joueurCreateDto.getPoste()));
			Selection selectionDuJoueur = selectionRepository.findById(joueurCreateDto.getSelection())
					.orElseThrow(()-> new ResourceNotFoundException("Selection","id",joueurCreateDto.getSelection()));
			Club clubDuJoueur = clubRepository.findById(joueurCreateDto.getClub())
					.orElseThrow(()-> new ResourceNotFoundException("Club","id",joueurCreateDto.getClub()));
			
			joueurEntite.setName(joueurCreateDto.getName());
			joueurEntite.setPrenom(joueurCreateDto.getPrenom());
			joueurEntite.setDateNaissance(joueurCreateDto.getDateNaissance());
			joueurEntite.setClassement(joueurCreateDto.getClassement());
			joueurEntite.setNbrPointObtenu(joueurCreateDto.getNbrPointObtenu());
			joueurEntite.setAnneeRecompense(joueurCreateDto.getAnneeRecompense());
			joueurEntite.setClub(clubDuJoueur);
			joueurEntite.setSelection(selectionDuJoueur);
			joueurEntite.setPoste(posteDuJoueur);
					
			Joueur JoueurUpdated = joueurRepository.save(joueurEntite);
			
			return mapEntityToDTO(JoueurUpdated);
		}
	
	public Map<String,Boolean> deleteJoueur(long joueurId) {
		Joueur entityJoueur = joueurRepository.findById(joueurId)
				.orElseThrow(()->new ResourceNotFoundException("Joueur","id", joueurId ));
		joueurRepository.delete(entityJoueur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}

	@Override
	public List<JoueurDTO> SearchJoueurByNameOrPrenom(String name) {
		List<Joueur> searchJoueurByName = joueurRepository.SearchByNameOrPrenom(name);
		List<JoueurDTO> joueurs = searchJoueurByName.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		return joueurs;
	}
			
	}

