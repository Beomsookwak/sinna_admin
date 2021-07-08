package kr.co.sist.service;

import java.text.DecimalFormat;

public class test {

	public static void main(String[] args) {
		
		DecimalFormat df = new DecimalFormat("###,###");
		
		int a = 200000;
		
		System.out.println( df.format(a) );

	}

}
