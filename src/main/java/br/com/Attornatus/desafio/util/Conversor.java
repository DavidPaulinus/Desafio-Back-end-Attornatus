package br.com.Attornatus.desafio.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conversor {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Date toDate(String data) throws ParseException {
		return sdf.parse(data);
	}
}
