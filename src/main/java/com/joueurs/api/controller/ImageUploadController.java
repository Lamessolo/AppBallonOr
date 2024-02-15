package com.joueurs.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageUploadController {

	 @Value("${upload.path}")
	    private String uploadPath;

	    @PostMapping("/upload")
	    @Operation(summary = "Upload image serveur", description = "This endpoint upload image on serveur")
	    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
	        try {
	            if (file.isEmpty()) {
	                return ResponseEntity.badRequest().body("Fichier vide");
	            }

	            // Assurez-vous que le dossier de téléchargement existe
	            Path uploadPath = Paths.get(this.uploadPath);
	            if (Files.notExists(uploadPath)) {
	                Files.createDirectories(uploadPath);
	            }

	            // Obtenez le nom du fichier original
	            String originalFilename = file.getOriginalFilename();

	            // Générez un nom de fichier unique pour éviter les collisions
	            String uniqueFileName = System.currentTimeMillis() + "_" + originalFilename;

	            // Chemin complet du fichier sur le serveur
	            Path filePath = uploadPath.resolve(uniqueFileName);

	            // Enregistrez le fichier sur le serveur
	            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(filePath.toFile()));

	            URI location = UriComponentsBuilder.fromPath("/api/images/" + uniqueFileName).build().toUri();
                  System.out.println(location);
	            return ResponseEntity.created(location).body("Fichier téléchargé avec succès");
	        } catch (IOException e) {
	            return ResponseEntity.status(500).body("Échec du téléchargement du fichier");
	        }
	    }
}
