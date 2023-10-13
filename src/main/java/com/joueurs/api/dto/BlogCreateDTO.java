package com.joueurs.api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogCreateDTO {

    public String title;
	
	public String description;
	
	public String content;
	
	public String imageUrl;
	
	public String urlHandle;
	
	public String author;
	
	public boolean isVisible;
	
	public Date publishedDate;
}
