package br.com.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String nome) {
		try {
			return URLDecoder.decode(nome, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String s){
		String[] vetor = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vetor.length; i++) {
			list.add(Integer.parseInt(vetor[i]));
		}
		
		//OU UTILIZANDO A EXPRESSÃO LAMBDA.
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
		return list;
	}
}