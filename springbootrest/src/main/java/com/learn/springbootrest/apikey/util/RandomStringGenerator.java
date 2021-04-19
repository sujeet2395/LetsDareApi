package com.learn.springbootrest.apikey.util;

import java.util.Random;

public class RandomStringGenerator {
	public static String nextString(int length, String prefix)
	{
		String charSet= "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int charSetLength=charSet.length();
		StringBuilder sb=new StringBuilder();
		Random r=new Random();
		if(length>prefix.length())
			sb.append(prefix);
		for(int i=prefix.length(); i<length; i++)
		{
			sb.append(charSet.charAt(r.nextInt(charSetLength)));
		}
		return sb.toString();
	}
}
