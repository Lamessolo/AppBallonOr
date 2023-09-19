package com.joueurs.api.utils;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;


public class JoueurHelpers {

	private JoueurHelpers() {
		  throw new IllegalStateException("La Date de Naissance ou années de recompense npeuvent etre nuls");
	}
	
	
	public static int calculAgeJoueurRecompense(Date dateNaissanceJoueur, String anneeRecompense) {
		
		if(dateNaissanceJoueur == null || anneeRecompense == null) throw new IllegalArgumentException("La Date de Naissance ou années de recompense npeuvent etre nuls");
				
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(dateNaissanceJoueur);
		 int annee = calendar.get(Calendar.YEAR);

		 return Integer.parseInt(anneeRecompense) - annee ;	
	}
}
