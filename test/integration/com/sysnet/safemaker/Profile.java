package com.sysnet.safemaker;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Profile {
	
	private static String profileKey;
	private static String keyValue;
	

	public Profile(String profileKey, String keyValue) {
		// TODO Auto-generated constructor stub
	}

	public static void splitLine(String stringCellValue, WebDriver driver, Properties clientProps) {
		// TODO Auto-generated method stub
		String[] cellLine=getProfileKeyAndKeyValue(stringCellValue);
		
			for(String str : cellLine){
				if(!(str.equals(null))){
				splitProfileKeyAndKeyValue(str);
				
			System.out.println("proflileKey: "+profileKey);
			System.out.println("KeyValue: "+keyValue);
			}
			}
		}

	private static  Profile splitProfileKeyAndKeyValue(String str) {
		// TODO Auto-generated method stub
		String[] splitString = str.split(":");
		profileKey=splitString[0];
		keyValue=splitString[1];
		return new Profile(profileKey, keyValue);
		
	}

	private static String[] getProfileKeyAndKeyValue(String stringCellValue) {
		if(!(stringCellValue.equals(null))){
				String []filedElements= stringCellValue.split("\n");
				return filedElements;
			}else{
				return null;
			}
	}

}
