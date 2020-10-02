package com.rushabh.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String ConvertDateFormat(String p_date, String p_inputFormat, String p_outputFormat) {
		String l_returnDate = "";

		try {
			Date l_date = new SimpleDateFormat(p_inputFormat).parse(p_date);

			DateFormat l_dateFormat = new SimpleDateFormat(p_outputFormat);
			l_returnDate = l_dateFormat.format(l_date);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return l_returnDate;
	}
}
