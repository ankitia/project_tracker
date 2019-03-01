package com.ia.web.controller;


import java.util.Scanner;

public class temp {

	 public static void main( String args[] ){  
		  
		  Scanner scanner = new Scanner( System.in );  
		  System.out.print("Enter ur age ");  
		    
		  int value = scanner.nextInt();
		  System.out.println(value );
		  
		  assert value>=18:" Not valid";  
		  
		  System.out.println("value is "+value);  
		 }   
        
		  
	
}
