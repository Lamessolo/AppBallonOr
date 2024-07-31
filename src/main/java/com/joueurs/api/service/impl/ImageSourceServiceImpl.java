package com.joueurs.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.joueurs.api.dto.ImageSourceCreateDTO;
import com.joueurs.api.dto.ImageSourceDTO;

import com.joueurs.api.entity.ImageSource;
import com.joueurs.api.exception.ImageSourceNotFoundException;
import com.joueurs.api.repository.ImageSourceRepository;
import com.joueurs.api.service.IImageSourceService;
import com.joueurs.api.utils.PaginationImageSourceResponse;


@Service
public class ImageSourceServiceImpl implements IImageSourceService {
	
	
	@Autowired
	private ImageSourceRepository imageSourceRepository;
	private ModelMapper mapper;
	
	public ImageSourceServiceImpl(ImageSourceRepository imageSourceRepository, ModelMapper mapper) {
		this.imageSourceRepository = imageSourceRepository;
		this.mapper = mapper;
	}
	
	private ImageSourceDTO mapEntityToDto(ImageSource imageSource) 
	{		
		return mapper.map(imageSource, ImageSourceDTO.class);
	}
	
	private ImageSource mapDtoToEntity(ImageSourceCreateDTO imageSourceCreateDto) 
	{		
		return mapper.map(imageSourceCreateDto, ImageSource.class);
	}
	
	

	@Override
	public ImageSourceDTO createImageSource(ImageSourceCreateDTO imageCreateDto) {
		
		if(imageCreateDto.getFileName().isBlank() || imageCreateDto.getFileName().isEmpty()) {
			throw new IllegalArgumentException("L'iamge doit avoir un nom d'au moins deux caracteres !");
		}
		
		ImageSource newImageSource = mapDtoToEntity(imageCreateDto);
		newImageSource.setFileName(imageCreateDto.getFileName());
		newImageSource.setFilePath(imageCreateDto.getFilePath());
		
		imageSourceRepository.save(newImageSource);
		
		return mapEntityToDto(newImageSource);
		
	}

	@Override
	public PaginationImageSourceResponse getAllImageSource(int pageNo, int pageSize, String sortBy) {
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<ImageSource> listeDesImages = imageSourceRepository.findAll(pageable);
		List<ImageSource> images = listeDesImages.getContent();
		List<ImageSourceDTO> content = images.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
		PaginationImageSourceResponse pageImagesSourceResponse = new PaginationImageSourceResponse();
		pageImagesSourceResponse.setContent(content);
		pageImagesSourceResponse.setPageNo(listeDesImages.getNumber());
		pageImagesSourceResponse.setPageSize(listeDesImages.getSize());
		pageImagesSourceResponse.setTotalElements(listeDesImages.getTotalElements());
		pageImagesSourceResponse.setTotalPages(listeDesImages.getTotalPages());
		pageImagesSourceResponse.setLast(listeDesImages.isLast());
		return pageImagesSourceResponse;	
	}
	
	
	public ImageSourceDTO findImageSourceById(long imageSourceId){
		ImageSource imageSource = imageSourceRepository.findById(imageSourceId)
				.orElseThrow(()-> new ImageSourceNotFoundException("Image", "id", imageSourceId));		
				return mapEntityToDto(imageSource);								
		}

	@Override
	public ImageSourceDTO updateImageSourceById(long imageSourceId, ImageSourceDTO imageSourceDtoToUpdate) {
		  
		// Recuperer l'entite Source by Id
				ImageSource imageSource = imageSourceRepository
						.findById(imageSourceId)
						.orElseThrow(()-> new ImageSourceNotFoundException("Image", "id", imageSourceId));
				
				imageSource.setFileName(imageSourceDtoToUpdate.getFileName());
				imageSource.setFilePath(imageSourceDtoToUpdate.getFilePath());
				
			ImageSource imageSourceUpdated = imageSourceRepository.save(imageSource);
				
				return mapEntityToDto(imageSourceUpdated);
				
				
	}
	
	

	
}
