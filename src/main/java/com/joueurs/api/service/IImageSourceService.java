package com.joueurs.api.service;

import com.joueurs.api.dto.ImageSourceCreateDTO;
import com.joueurs.api.dto.ImageSourceDTO;
import com.joueurs.api.utils.PaginationImageSourceResponse;


public interface IImageSourceService {

	ImageSourceDTO createImageSource(ImageSourceCreateDTO imageCreateDto);
	
	PaginationImageSourceResponse getAllImageSource(int pageNo,int pageSize,String sortBy);
	
	ImageSourceDTO  findImageSourceById(long imageSourceId);
	
	ImageSourceDTO  updateImageSourceById(long imageSourceId, ImageSourceDTO imageSourceDtoToUpdate);
}
