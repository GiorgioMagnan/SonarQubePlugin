package com.juliasoft.sonarqube.plugin;

public class TestClass {
	
		  /*int     foo1() { return 0; }
		  void    foo2(int value) { }
		  int     foo3(int value) { return 0; } // Noncompliant
		  Object  foo4(int value) { return null; }
		  TestClass foo5(TestClass value) {return null; } // Noncompliant
		 
		  int     foo6(int value, String name) { return 0; }
		  int     foo7(int ... values) { return 0;}*/
	
	int foo1(){
		while(true){
			return 1;
		}
	}
	
	int foo2(int a){
		if(a>1){
			for(int i=0; i<a; i++){
				do{
					return 1;
				}while(true);
			}
		}
		else{
			for(int i=a; i>0; i--){
				do{
					return 1;
				}while(true);
			}
		}
	}
	
	int foo3(){
		int i=0;
		int j;
		int z;
		if(i>0){
			i++;
		}
		while(true){
			int k;
			for(int p=0;p<10;p++){
				int m;
				do{ // Noncompliant
					return 1;
				}while(true); 
			}
		}
	}
	
	int foo4(int[] array, int b, int c){
		for(int element:array){
			int i=0;
			while(i<b){
				for(int j=0; j<c; j++){ // Noncompliant
					return 0;
				}
				i++;
			}
		}
	}
	
	private int foo5(int c){
		for(int j=0; j<c; j++){
			return 0;
		}
	}
	
	int foo4_modify(int[] array, int b, int c){
		int val;
		for(int element:array){
			int i=0;
			while(i<b){
				val = foo5(c);
				i++;
			}
		}
		return val;
	}
}
