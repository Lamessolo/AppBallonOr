package com.joueurs.api.service.impl;
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
		
		return mapper.map(joueur, JoueurDTO.class);			
	}
	
	public  Joueur mapDtoToEntity(JoueurCreateDTO joueurCreateDto) {

		return mapper.map(joueurCreateDto, Joueur.class);		
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
			List<JoueurDTO> content = joueurs.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
			
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
					
			Joueur joueurUpdated = joueurRepository.save(joueurEntite);
			
			return mapEntityToDTO(joueurUpdated);
		}
	
	public Map<String,Boolean> deleteJoueur(long joueurId) {
		Joueur entityJoueur = joueurRepository.findById(joueurId)
				.orElseThrow(()->new ResourceNotFoundException("Joueur","id", joueurId ));
		joueurRepository.delete(entityJoueur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}


	public List<JoueurDTO> searchJoueurByPoste(long posteId) {
		List<Joueur> searchJoueurByPoste = joueurRepository.findByPosteId(posteId);
		return searchJoueurByPoste.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
	}

	public List<JoueurDTO> searchJoueurByAnnee(String annee) {
		List<Joueur> searchJoueurByAnnee = joueurRepository.searchJoueurByAnnee(annee);
		return  searchJoueurByAnnee.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
	}

	@Override
	public  PaginationResponse  findByPosteId(int pageNo,int pageSize,String sortBy,long byPosteId) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByPosteId = joueurRepository.findByPosteId(byPosteId,pageable);
		List<Joueur> joueursByPosteId = listeDesJoueursByPosteId.getContent();
		List<JoueurDTO> contentJoueurByPosteId = joueursByPosteId.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByPosteId);
		pageJoueursResponse.setPageNo(listeDesJoueursByPosteId.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByPosteId.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByPosteId.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByPosteId.getTotalPages());
		pageJoueursResponse.setLast(listeDesJoueursByPosteId.isLast());
		return pageJoueursResponse;	
	
	}
		
	@Override
    public PaginationResponse findByAnnee(int pageNo, int pageSize, String sortBy, String byAnnee) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByAnnee = joueurRepository.findByAnneeRecompense(byAnnee,pageable);
		List<Joueur> joueursByAnnee = listeDesJoueursByAnnee.getContent();
		List<JoueurDTO> contentJoueurByAnnee = joueursByAnnee.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByAnnee);
		pageJoueursResponse.setPageNo(listeDesJoueursByAnnee.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByAnnee.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByAnnee.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByAnnee.getTotalPages());
		pageJoueursResponse.setLast(listeDesJoueursByAnnee.isLast());
		return pageJoueursResponse;
	}

	@Override
	public PaginationResponse findByClassementPosition(int pageNo, int pageSize, String sortBy, int byClassementPosition) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByClassementPosition = joueurRepository.findByClassement(byClassementPosition,pageable);
		List<Joueur> joueursByClassementPosition = listeDesJoueursByClassementPosition.getContent();
		List<JoueurDTO> contentJoueurByClassementPosition = joueursByClassementPosition.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByClassementPosition);
		pageJoueursResponse.setPageNo(listeDesJoueursByClassementPosition.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByClassementPosition.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByClassementPosition.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByClassementPosition.getTotalPages());
		pageJoueursResponse.setLast(listeDesJoueursByClassementPosition.isLast());
		return pageJoueursResponse;
	}

	@Override
	public PaginationResponse findByClubId(int pageNo, int pageSize, String sortBy, int byClubId) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByClub = joueurRepository.findByClubId(byClubId,pageable);
		List<Joueur> joueursByClub = listeDesJoueursByClub.getContent();
		List<JoueurDTO> contentJoueurByClub = joueursByClub.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByClub);
		pageJoueursResponse.setPageNo(listeDesJoueursByClub.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByClub.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByClub.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByClub.getTotalPages());
		
		return pageJoueursResponse;
	}

	@Override
	public PaginationResponse findJoueurByMotClef(int pageNo, int pageSize, String sortBy, String byMotClef) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByMotClef = joueurRepository.SearchByNameOrPrenom(byMotClef,pageable);
		List<Joueur> joueursByMotClef = listeDesJoueursByMotClef.getContent();
		List<JoueurDTO> contentJoueurByMotClef = joueursByMotClef
				.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByMotClef);
		pageJoueursResponse.setPageNo(listeDesJoueursByMotClef.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByMotClef.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByMotClef.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByMotClef.getTotalPages());
		
		return pageJoueursResponse;
	}

	@Override
	public PaginationResponse findJoueurByParametres( 
			Long posteId,
			Long classement,
			String anneeRecompense,
			int pageNo,
			int pageSize,
			String sortBy) {
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByParametres = joueurRepository.findJoueurByAnneeRecompenseAndPosteIdAndClassement(posteId,classement,anneeRecompense,pageable);
		List<Joueur> joueursByMotClef = listeDesJoueursByParametres.getContent();
		List<JoueurDTO> contentJoueurByParametres = joueursByMotClef
				.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByParametres);
		pageJoueursResponse.setPageNo(listeDesJoueursByParametres.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByParametres.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByParametres.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByParametres.getTotalPages());	
		return pageJoueursResponse;
	}
	
			
	}

