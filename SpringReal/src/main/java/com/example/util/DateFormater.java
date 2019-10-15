package com.example.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormater {
	
	public static Date strintToDate(String sdate) {
		SimpleDateFormat sdf =null;
		Date date=null;
		sdf=new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = sdf.parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
		
	}

	public static String dateToString(Date date) {
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		String result=df.format(date);
		return result;
	}
}
