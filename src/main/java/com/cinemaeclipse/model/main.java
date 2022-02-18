package com.cinemaeclipse.model;

import com.cinemaeclipse.util.Utility;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean x= Utility.checkEmail("helloexample.com");
		System.out.println(x);
		boolean y = Utility.checkPassword("0Ciaomario!");
		System.out.println(y);
	}

}
