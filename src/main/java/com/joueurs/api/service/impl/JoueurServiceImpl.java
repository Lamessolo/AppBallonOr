package com.joueurs.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.joueurs.api.entity.Joueur;
import com.joueurs.api.entity.Poste;
import com.joueurs.api.entity.Selection;
import com.joueurs.api.entity.Titre;
import com.joueurs.api.repository.ClubRepository;
import com.joueurs.api.repository.JoueurRepository;
import com.joueurs.api.repository.PosteRepository;
import com.joueurs.api.repository.SelectionRepository;
import com.joueurs.api.repository.TitreRepository;
import com.joueurs.api.service.IJoueurService;
import com.joueurs.api.utils.PaginationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoueurServiceImpl implements IJoueurService {

	private final JoueurRepository joueurRepository;
	private final PosteRepository posteRepository ;
	private final SelectionRepository selectionRepository;
	private final TitreRepository  titreRepository;
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
		 // Validation des données d'entrée
	    if (joueurCreateDto == null || joueurCreateDto.getName() == null)
	    {
	        throw new IllegalArgumentException("Données d'entrée invalides.");
	    }
				
		// Je transforme le JoueurDTO en une nouvelle entité Joueur
		       Joueur createNewJoueur = mapDtoToEntity(joueurCreateDto);   
				Optional<Poste> posteNewOptional  = posteRepository.findById(joueurCreateDto.getPoste());					
				Optional<Selection> selectionNewOptional  = selectionRepository.findById(joueurCreateDto.getSelection());					
				Optional<Club> clubNewOptional  = clubRepository.findById(joueurCreateDto.getClub());
						
				
				// Vérifiez si les objets Optionals contiennent des valeurs avant d'extraire les entités
				if (posteNewOptional.isPresent() && selectionNewOptional.isPresent() && clubNewOptional.isPresent()){
				    Poste posteNew = posteNewOptional.get();
				    Selection selectionNew = selectionNewOptional.get();
				    Club clubNew = clubNewOptional.get();
				    
				    createNewJoueur.setPoste(posteNew);
				    createNewJoueur.setSelection(selectionNew);
				    createNewJoueur.setClub(clubNew);
				
				// Je sauve une entité dans la base			 
				joueurRepository.save(createNewJoueur);
				
				return mapEntityToDTO(createNewJoueur);
				}else {
				    // Gérez le cas où l'une des entités n'a pas été trouvée
				    // Vous pouvez générer une exception, renvoyer une erreur, ou prendre d'autres mesures appropriées.
				    throw new IllegalArgumentException("Une erreur est survenue lors de la creation du joueur."); // ou une autre valeur appropriée
				}
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
		
			Optional<Joueur> entityJoueurOptional = joueurRepository.findById(joueurId);
			if (entityJoueurOptional.isPresent())
			{ Joueur joueurFindById   = entityJoueurOptional.get();
			return mapEntityToDTO(joueurFindById);
			}
			else 
			{
				throw new IllegalArgumentException("L'id du joueur n'est pas bon");
			}
							
	}

	@Override
	public JoueurDTO updateJoueur(long joueurId, JoueurCreateDTO joueurCreateDto) {
		
			// Recuperer l'entite Joueur by Id
			Optional<Joueur> joueurEntiteOptional = joueurRepository.findById(joueurId);
					
			
			Optional<Poste> posteJoueurOptional = posteRepository.findById(joueurCreateDto.getPoste());
					
			Optional<Selection> selectionJoueurOptional = selectionRepository.findById(joueurCreateDto.getSelection());
					
			Optional<Club> clubJoueurOptional = clubRepository.findById(joueurCreateDto.getClub());
					
			
					// Vérifiez si les objets Optionals contiennent des valeurs avant d'extraire les entités
					if (joueurEntiteOptional.isPresent()&&posteJoueurOptional.isPresent() 
							&& selectionJoueurOptional.isPresent() 
							&& clubJoueurOptional.isPresent())
					{
						    Joueur joueurNew = joueurEntiteOptional.get();
						    Poste posteNew = posteJoueurOptional.get();
						    Selection selectionNew = selectionJoueurOptional.get();
						    Club clubNew = clubJoueurOptional.get();
						    
						    joueurNew.setName(joueurCreateDto.getName());
							joueurNew.setPrenom(joueurCreateDto.getPrenom());
							joueurNew.setDateNaissance(joueurCreateDto.getDateNaissance());
							joueurNew.setClassement(joueurCreateDto.getClassement());
							joueurNew.setImageUrl(joueurCreateDto.getImageUrl());
							joueurNew.setSurnom(joueurCreateDto.getSurnom());
							joueurNew.setDescription(joueurCreateDto.getDescription());
							joueurNew.setNbrPointObtenu(joueurCreateDto.getNbrPointObtenu());
							joueurNew.setAnneeRecompense(joueurCreateDto.getAnneeRecompense());
							joueurNew.setClub(clubNew);
							joueurNew.setSelection(selectionNew);
							joueurNew.setPoste(posteNew);
							
							Joueur joueurUpdated = joueurRepository.save(joueurNew);
							return mapEntityToDTO(joueurUpdated);
					}else{
						// Gérez le cas où l'une des entités n'a pas été trouvée
					    // Vous pouvez générer une exception, renvoyer une erreur, ou prendre d'autres mesures appropriées.
					    return null; // ou une autre valeur appropriée
					}
																
		}
	
	public Map<String,Boolean> deleteJoueur(long joueurId) {
		Optional<Joueur> entityJoueurOptional = joueurRepository.findById(joueurId);
			Joueur entityJoueur = entityJoueurOptional.get();
				//.orElseThrow(()->new ResourceNotFoundException("Joueur","id", joueurId ));
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
	public PaginationResponse findJoueurByPosteAndClassementParametres( 
			int posteId,
			int classement,
			int pageNo,
			int pageSize,
			String sortBy) {
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Joueur> listeDesJoueursByParametres = joueurRepository.findJoueurByPosteIdAndClassement(posteId,classement,pageable);
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

	@Override
	public PaginationResponse findJoueurByParametres(int posteId,int classement, String anneeRecompense, int pageNo, int pageSize,
			String sortBy) 
	{
		
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

	@Override
	public JoueurDTO assignedTitreToJoueur(long joueurId, int titreId) {
		Set<Titre> listeTitre = null;
		Optional<Joueur> joueurOptional = joueurRepository.findById(joueurId);
		Optional<Titre> titreToAdd = titreRepository.findById(titreId);
		
		if(joueurOptional.isPresent()&& titreToAdd.isPresent()) {
		listeTitre = joueurOptional.get().getAssignedTitres();
		listeTitre.add(titreToAdd.get());
		joueurOptional.get().setAssignedTitres(listeTitre);
		joueurRepository.save(joueurOptional.get());
		return mapEntityToDTO(joueurOptional.get());
		}
		else {throw new IllegalArgumentException("Un indentitifant est incorrect titre ou Joueur");}
		
		
	}
		
	}

