package com.cunha.calculator.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parseObject(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for(O object: origin) {
			destinationObjects.add(mapper.map(object, destination));
		}
		return destinationObjects;
	}
}
