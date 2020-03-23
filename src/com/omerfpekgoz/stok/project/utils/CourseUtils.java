package com.omerfpekgoz.stok.project.utils;

import com.omerfpekgoz.stok.project.models.Staff;

public class CourseUtils {

	
	public static Staff loginedUser ;
	
	public static String getValue(Object value) {
		if(value==null) 
			return "";
		else 
			return value.toString();
	}
}
