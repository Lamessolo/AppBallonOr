package com.joueurs.api.utils;

import lombok.Data;

@Data
public class ConstanteApp {
	
	public static final String DEFAULT_PAGE_NUMBER ="0";
	
	public static final String DEFAULT_PAGE_SIZE ="15";
	
	public static final String DEFAULT_SORT_BY ="name";
	
	public static final String DEFAULT_SORT_BY_FILENAME ="fileName";
	
	public static final String DEFAULT_SORT_BY_TITLE ="title";
	
	public static final String DEFAULT_SORT_DIRECTION="asc";
	
	public static final String REVERSE_SORT_DIRECTION="desc";
	
	public static final String DEFAULT_SORT_BY_YEAR ="anneeRecompense";
}
