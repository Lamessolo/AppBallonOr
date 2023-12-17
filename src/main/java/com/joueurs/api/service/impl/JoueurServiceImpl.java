package com.joueurs.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.joueurs.api.dto.JoueurCreateDTO;
import com.joueurs.api.dto.JoueurDTO;
import com.joueurs.api.entity.Club;
import com.joueurs.api.entity.Confederation;
import com.joueurs.api.entity.Joueur;
import com.joueurs.api.entity.Poste;
import com.joueurs.api.entity.Selection;
import com.joueurs.api.entity.Titre;
import com.joueurs.api.exception.ClubNotFoundException;
import com.joueurs.api.exception.ConfederationNotFoundException;
import com.joueurs.api.exception.JoueurNotFoundException;
import com.joueurs.api.exception.PosteNotFoundException;
import com.joueurs.api.exception.SelectionNotFoundException;
import com.joueurs.api.exception.TitreNotFoundException;
import com.joueurs.api.repository.ClubRepository;
import com.joueurs.api.repository.ConfederationRepository;
import com.joueurs.api.repository.JoueurRepository;
import com.joueurs.api.repository.PosteRepository;
import com.joueurs.api.repository.SelectionRepository;
import com.joueurs.api.repository.TitreRepository;
import com.joueurs.api.service.IJoueurService;
import com.joueurs.api.utils.PaginationResponse;

@Service
public class JoueurServiceImpl implements IJoueurService {

	private  JoueurRepository joueurRepository;
	private  PosteRepository posteRepository ;
	private  SelectionRepository selectionRepository;
	private  TitreRepository  titreRepository;
	private  ClubRepository clubRepository;
	private  ConfederationRepository confederationRepository;
	
	private  ModelMapper mapper ;
	
	
	public JoueurServiceImpl(JoueurRepository joueurRepository, PosteRepository posteRepository,
			SelectionRepository selectionRepository,
			TitreRepository titreRepository, 
			ClubRepository clubRepository,
			ConfederationRepository confederationRepository,
			ModelMapper mapper) {
		this.joueurRepository = joueurRepository;
		this.posteRepository = posteRepository;
		this.selectionRepository = selectionRepository;
		this.titreRepository = titreRepository;
		this.clubRepository = clubRepository;
		this.confederationRepository = confederationRepository;
		this.mapper = mapper;
	}

	public  JoueurDTO mapEntityToDto(Joueur joueur) {
		
		return mapper.map(joueur, JoueurDTO.class);	
	}
	
	public  Joueur mapDtoToEntity(JoueurCreateDTO joueurCreateDto) {
		return mapper.map(joueurCreateDto, Joueur.class);
	}
	
	@Override
	public JoueurDTO createJoueur(JoueurCreateDTO joueurCreateDto) {
		 // Validation des données d'entrée
	    if (joueurCreateDto == null || joueurCreateDto.getName() == null)
	    {
	        throw new IllegalArgumentException("Données d'entrée invalides.");
	    }
				
		// Je transforme le JoueurDTO en une nouvelle entité Joueur
		       Joueur createNewJoueur = mapDtoToEntity(joueurCreateDto);   
			Poste poste  = posteRepository
					.findById(joueurCreateDto.getPoste())
					.orElseThrow(()-> new PosteNotFoundException("Poste","id", joueurCreateDto.getPoste()));
			
			Selection selection  = selectionRepository
					.findById(joueurCreateDto.getSelection())
					.orElseThrow(()-> new SelectionNotFoundException("Selection","id", joueurCreateDto.getSelection()));
							
			
			Confederation confederation  = confederationRepository
					.findById(joueurCreateDto.getConfederation())
					.orElseThrow(()-> new ConfederationNotFoundException("Confederation","id", joueurCreateDto.getConfederation()));
			
			Club club = clubRepository
					   .findById(joueurCreateDto.getClub())
					   .orElseThrow(()-> new ClubNotFoundException("Club","id", joueurCreateDto.getClub()));
					 		    
				    createNewJoueur.setPoste(poste);
				    createNewJoueur.setSelection(selection);
				    createNewJoueur.setClub(club);
				    createNewJoueur.setConfederation(confederation);
				
				// Je sauve une entité dans la base			 
				joueurRepository.save(createNewJoueur);
				
				return mapEntityToDto(createNewJoueur);				
	}

	@Override
	public PaginationResponse getAllJoueur(int pageNo,int pageSize,String sortBy) {
				
			PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
			Page<Joueur> listeDesJoueurs = joueurRepository.findAll(pageable);
			List<Joueur> joueurs = listeDesJoueurs.getContent();
			List<JoueurDTO> content = joueurs.stream().map(this::mapEntityToDto).collect(Collectors.toList());
			
			PaginationResponse pageJoueursResponse = new PaginationResponse();
			pageJoueursResponse.setContent(content);
			pageJoueursResponse.setPageNo(listeDesJoueurs.getNumber());
			pageJoueursResponse.setPageSize(listeDesJoueurs.getSize());
			pageJoueursResponse.setTotalElements(listeDesJoueurs.getTotalElements());
			pageJoueursResponse.setTotalPages(listeDesJoueurs.getTotalPages());
			pageJoueursResponse.setLast(listeDesJoueurs.isLast());
			return pageJoueursResponse;	
		
	}
	
	public JoueurDTO findJoueurById(long joueurId){
	Joueur joueur = joueurRepository
			.findById(joueurId)
			.orElseThrow(()-> new JoueurNotFoundException("Joueur", "id", joueurId));		
			return mapEntityToDto(joueur);								
	}

	@Override
	public JoueurDTO updateJoueur(long joueurId, JoueurCreateDTO joueurCreateDto) {	
	// Recuperer l'entite Joueur by Id
		Joueur joueur = joueurRepository
				.findById(joueurId)
				.orElseThrow(()-> new JoueurNotFoundException("Joueur", "id", joueurId));
						
		Poste poste = posteRepository
				.findById(joueurCreateDto.getPoste())
				.orElseThrow(()-> new PosteNotFoundException("Poste","id", joueurCreateDto.getPoste()));
				
	  Selection selection = selectionRepository
			  .findById(joueurCreateDto.getSelection())
			  .orElseThrow(()-> new SelectionNotFoundException("Selection","id", joueurCreateDto.getSelection()));
					
	  Club club  = clubRepository
			  .findById(joueurCreateDto.getClub())
			  .orElseThrow(()-> new SelectionNotFoundException("Club","id", joueurCreateDto.getClub()));
	  
	  Confederation confederation  = confederationRepository
				.findById(joueurCreateDto.getConfederation())
				.orElseThrow(()-> new ConfederationNotFoundException("Confederation","id", joueurCreateDto.getConfederation()));
		
									  			    
		joueur.setName(joueurCreateDto.getName());
		joueur.setPrenom(joueurCreateDto.getPrenom());
		joueur.setDateNaissance(joueurCreateDto.getDateNaissance());
		joueur.setClassement(joueurCreateDto.getClassement());
		joueur.setImageUrl(joueurCreateDto.getImageUrl());
		joueur.setSurnom(joueurCreateDto.getSurnom());
		joueur.setRate(joueurCreateDto.getRate());
		joueur.setDescription(joueurCreateDto.getDescription());
		joueur.setNbrPointObtenu(joueurCreateDto.getNbrPointObtenu());
		joueur.setAnneeRecompense(joueurCreateDto.getAnneeRecompense());
		joueur.setClub(club);
		joueur.setSelection(selection);
		joueur.setPoste(poste);
		joueur.setConfederation(confederation);
							
		Joueur joueurUpdated = joueurRepository.save(joueur);
				return mapEntityToDto(joueurUpdated);															
		}
	
	public Map<String,Boolean> deleteJoueur(long joueurId) {
		Joueur joueur = joueurRepository
				.findById(joueurId)
				.orElseThrow(()-> new JoueurNotFoundException("Joueur", "id", joueurId));
		joueurRepository.delete(joueur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;	
	}

	public List<JoueurDTO> searchJoueurByPoste(int posteId) {
		List<Joueur> searchJoueurByPoste = joueurRepository.findByPosteId(posteId);
		return searchJoueurByPoste.stream().map(this::mapEntityToDto).collect(Collectors.toList());
	}

	public List<JoueurDTO> searchJoueurByAnnee(String annee) {
		List<Joueur> searchJoueurByAnnee = joueurRepository.searchJoueurByAnnee(annee);
		return  searchJoueurByAnnee.stream().map(this::mapEntityToDto).collect(Collectors.toList());
	}

	@Override
	public  PaginationResponse  findByPosteId(long byPosteId,int pageNo,int pageSize,String sortBy) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByPosteId = joueurRepository.findByPosteId(byPosteId,pageable);
		List<Joueur> joueursByPosteId = listeDesJoueursByPosteId.getContent();
		List<JoueurDTO> contentJoueurByPosteId = joueursByPosteId.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
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
    public PaginationResponse findByAnnee(String year ,int pageNo, int pageSize, String sortBy) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByAnnee = joueurRepository.findByAnneeRecompense(year,pageable);
		List<Joueur> joueursByAnnee = listeDesJoueursByAnnee.getContent();
		List<JoueurDTO> contentJoueurByAnnee = joueursByAnnee.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
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
		List<JoueurDTO> contentJoueurByClassementPosition = joueursByClassementPosition.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
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
	public PaginationResponse findByClubId(int pageNo, int pageSize, String sortBy, long byClubId) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByClub = joueurRepository.findByClubId(byClubId,pageable);
		List<Joueur> joueursByClub = listeDesJoueursByClub.getContent();
		List<JoueurDTO> contentJoueurByClub = joueursByClub.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
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
				.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByMotClef);
		pageJoueursResponse.setPageNo(listeDesJoueursByMotClef.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByMotClef.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByMotClef.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByMotClef.getTotalPages());
		
		return pageJoueursResponse;
	}

	
	@Override
	public PaginationResponse findJoueurByPosteAndClassementParametres( 
			long posteId,
			int classement,
			int pageNo,
			int pageSize,
			String sortBy) {
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByParametres = joueurRepository.findJoueurByPosteIdAndClassement(posteId,classement,pageable);
		List<Joueur> joueursByMotClef = listeDesJoueursByParametres.getContent();
		List<JoueurDTO> contentJoueurByParametres = joueursByMotClef
				.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByParametres);
		pageJoueursResponse.setPageNo(listeDesJoueursByParametres.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByParametres.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByParametres.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByParametres.getTotalPages());	
		return pageJoueursResponse;
	}

	@Override
	public PaginationResponse findJoueurByParametres(long posteId,int classement, String anneeRecompense, int pageNo, int pageSize,
			String sortBy) 
	{
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByParametres = joueurRepository.findJoueurByAnneeRecompenseAndPosteIdAndClassement(posteId,classement,anneeRecompense,pageable);
		List<Joueur> joueursByMotClef = listeDesJoueursByParametres.getContent();
		List<JoueurDTO> contentJoueurByParametres = joueursByMotClef
				.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByParametres);
		pageJoueursResponse.setPageNo(listeDesJoueursByParametres.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByParametres.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByParametres.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByParametres.getTotalPages());	
		return pageJoueursResponse;
	}

	@Override
	public JoueurDTO assignedTitreToJoueur(long joueurId, long titreId) {
		Set<Titre> listeTitre = null;
		Joueur joueur = joueurRepository
				.findById(joueurId)
				.orElseThrow(()-> new JoueurNotFoundException("Joueur", "id", joueurId));
				
		Titre titre = titreRepository
				.findById(titreId)
				.orElseThrow(()-> new TitreNotFoundException("Titre", "id",titreId));
				
		listeTitre = joueur.getAssignedTitres();
		listeTitre.add(titre);
		joueur.setAssignedTitres(listeTitre);
		joueurRepository.save(joueur);
		return mapEntityToDto(joueur);
		
	}

	

	@Override
	public PaginationResponse getVainqueurBallondOr(int pageNo, int pageSize, String sortBy) {
		
		pageSize = 30;
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesVainqueurBallondOr = joueurRepository.findVainqueurBallondOr(pageable);
		List<Joueur> vainqueurs = listeDesVainqueurBallondOr.getContent();
		List<JoueurDTO> content = vainqueurs
				.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(content);
		pageJoueursResponse.setPageNo(listeDesVainqueurBallondOr.getNumber());
		pageJoueursResponse.setPageSize(listeDesVainqueurBallondOr.getSize());
		pageJoueursResponse.setTotalElements(listeDesVainqueurBallondOr.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesVainqueurBallondOr.getTotalPages());	
		return pageJoueursResponse;
			
	}
		
	@Override
	public  PaginationResponse  findByConfederation(int pageNo,int pageSize,String sortBy,long confederationId) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByConfederation = joueurRepository.findByConfederationId(confederationId,pageable);
		List<Joueur> joueursByConfederationId = listeDesJoueursByConfederation.getContent();
		List<JoueurDTO> contentJoueurByConfederationId = joueursByConfederationId.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByConfederationId);
		pageJoueursResponse.setPageNo(listeDesJoueursByConfederation.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByConfederation.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByConfederation.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByConfederation.getTotalPages());
		pageJoueursResponse.setLast(listeDesJoueursByConfederation.isLast());
		return pageJoueursResponse;	
	
	}

	@Override
	public PaginationResponse findByRate(long byRate, int pageNo, int pageSize, String sortBy) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByRate = joueurRepository.findByRate(byRate,pageable);
		List<Joueur> joueursByRate = listeDesJoueursByRate.getContent();
		List<JoueurDTO> contentJoueurByRate = joueursByRate.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
		PaginationResponse pageJoueursResponse = new PaginationResponse();
		pageJoueursResponse.setContent(contentJoueurByRate);
		pageJoueursResponse.setPageNo(listeDesJoueursByRate.getNumber());
		pageJoueursResponse.setPageSize(listeDesJoueursByRate.getSize());
		pageJoueursResponse.setTotalElements(listeDesJoueursByRate.getTotalElements());
		pageJoueursResponse.setTotalPages(listeDesJoueursByRate.getTotalPages());
		
		return pageJoueursResponse;
	}

	

	


	




	
	




	


	}

