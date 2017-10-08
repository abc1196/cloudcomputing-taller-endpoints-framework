package com.cloudcomputing.dimayor.utils;

import org.modelmapper.ModelMapper;

public class ModelUtils {
	
	public static <T> T mapObjectToDTO(Object obj, Class<T> dtoClass) {
		ModelMapper modelMapper = new ModelMapper();		
		T t = modelMapper.map(obj, dtoClass);
		return t;
	}

}
