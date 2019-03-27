package com.osf.test.test;

public class StringBufferExam {
	public static void main(String[] args) {
		long sTime = System.currentTimeMillis();
		String str="";
		for(int i=1;i<10000;i++) {
			str += i+"t";
		}
		long eTime=System.currentTimeMillis() - sTime;
		System.out.println("sese  :" +eTime); 
	}
}
