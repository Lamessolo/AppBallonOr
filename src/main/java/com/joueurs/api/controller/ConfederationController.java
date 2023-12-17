package com.joueurs.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joueurs.api.dto.ConfederationDTO;
import com.joueurs.api.service.IConfederationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/confederation")
@CrossOrigin
public class ConfederationController {

	private final IConfederationService confederationService;
	
	@GetMapping
	public List<ConfederationDTO> getConfederations(){
		 
		return confederationService.getAllConfederation();
	}
}
