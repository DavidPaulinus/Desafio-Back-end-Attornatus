package br.com.Attornatus.desafio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversor {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static Date toDate(String data) throws ParseException {
		return sdf.parse(data);
	}
}
