package com.joueurs.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PosteDTO implements Serializable {

	private long id;
	
	private String name;
	
	private String description;
}
