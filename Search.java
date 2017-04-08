package com.example.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
	static int dis[][];
	static int mat[][];
	static int pre[][];
	static int M;
	static int N;
	static int lengthResult;
	static int[] path;
	static String stringResult;
	public static void Search(int M, int N) {
		int inf = 100000000;
		int rr, d, c ;
		int i;
		int limit = 50;
		for ( int r = 0; r < M; r ++ ) {
			dis[r][0] = 0;
		}
		boolean flag = false;
		for (c = 1; c <= N; c ++ ) {
			for ( int r = 0; r < M; r ++ ) {
				dis[r][c] = inf;
				for ( d = -1; d <= 1; d ++ ) {
					rr = r + d;
					if ( rr < 0 ) {
						rr += M;
					}
					if ( rr >= M ) {
						rr -= M;
					}
					
					if ( dis[r][c] > dis[rr][c-1] + mat[r][c] ) {
						dis[r][c] = dis[rr][c-1] + mat[r][c];
						pre[r][c] = rr;
					}
				}
			}
		}
		
		int length = inf;
		int v = 0;
		
		// check for SP exist
		c = N;
		while ( true ) {
			for ( int r = 0; r < M; r ++ ) {
				if ( dis[r][c] < length ) {
					length = dis[r][c];
					v = r;
				}
			}
			
			if ( length <= limit ) {
				break;
			}
			c --;
		}
		
		path = new int[N + 2];
		
		if ( c < N ) {
			flag = false;
		} else {
			flag = true;
			
		}

		N = c;
		for ( ; c > 0; c -- ) {
			path[c] = v+1;
			v = pre[v][c];
		}
		if(flag){
			stringResult = "YES";
		}
		else{
			stringResult = "NO";
		}
		lengthResult = length;
		/*
		
		System.out.println("Results:");
		if(flag){
		System.out.println("YES");
		}
		else{
			System.out.println("NO");
		}
		System.out.println(length);
		System.out.print("[");
		for(i = 1; i <= N; i++)
			System.out.print(path[i] + " ");
		System.out.print("]");
		System.out.println();*/
		
		
	}
	public static  int getResultLength(){
		return lengthResult;
	}
	public static  String getResultPath(){
		int i;
		String result;
		result = "[";
		for(i = 1; i <= N; i++){
			result = result + String.format("%d", path[i]);
			if(i!=N) result = result +" ";
		}
		result = result +"]";
		return result;
	}
	public static String getResultString(){
		return stringResult;
	}
	public static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
	public static void inputData(String filename){
		int i,j;
		Path path = Paths.get(filename);
		boolean isInvalidInput = false;
		try {
			//input from File
			List<String> lines = Files.readAllLines(path);
			M = lines.size();
			for(i = 0; i < lines.size(); i++){
				String[] strings = lines.get(i).split(" ");
				if(i == 0) N = strings.length;
				else if(strings.length != N){
					// invalid input.
					isInvalidInput = true;
				}
			}
			
			//initialize arrays
			mat = new int[M + 2][];
			dis = new int[M + 2][];
			pre = new int[M + 2][];
			for(i = 0; i < M + 2; i++){
				mat[i] = new int[N + 2];
				dis[i] = new int[N + 2];
				pre[i] = new int[N + 2];
				
			}
			
			
			//mat table 
			for(i = 0; i < M; i++){
				String[] strings = lines.get(i).split(" ");
				for(j = 0; j < N; j++){
					if(isInteger(strings[j])){
					mat[i][j + 1] = Integer.parseInt(strings[j]);
					}
					else{
						isInvalidInput = false;
					}
				}
			}
			
			
			/*for(i = 0; i < M+2; i++){
				for(j = 0; j < N+2; j++){
					System.out.print(mat[i][j]);
					System.out.print(" ");
				}
				System.out.println();
			}*/
			
			//call Search function
			if(!isInvalidInput){
				Search(M,N);
			}
			else{
				
				/*
				System.out.println("Results:");
				System.out.println("Invalid Matrix");
				System.out.println();*/
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		int i, j;
		String filename = "D:\\input.txt";
		inputData(filename);
		lengthResult = getResultLength();
//		Path path = Paths.get(filename);
//		boolean isInvalidInput = false;
//		try {
//			//input from File
//			List<String> lines = Files.readAllLines(path);
//			M = lines.size();
//			for(i = 0; i < lines.size(); i++){
//				String[] strings = lines.get(i).split(" ");
//				if(i == 0) N = strings.length;
//				else if(strings.length != N){
//					// invalid input.
//					isInvalidInput = true;
//				}
//			}
//			
//			//initialize arrays
//			mat = new int[M + 2][];
//			dis = new int[M + 2][];
//			pre = new int[M + 2][];
//			for(i = 0; i < M + 2; i++){
//				mat[i] = new int[N + 2];
//				dis[i] = new int[N + 2];
//				pre[i] = new int[N + 2];
//				
//			}
//			
//			
//			//mat table 
//			for(i = 0; i < M; i++){
//				String[] strings = lines.get(i).split(" ");
//				for(j = 0; j < N; j++){
//					if(isInteger(strings[j])){
//					mat[i][j + 1] = Integer.parseInt(strings[j]);
//					}
//					else{
//						isInvalidInput = false;
//					}
//				}
//			}
//			
//			
//			for(i = 0; i < M+2; i++){
//				for(j = 0; j < N+2; j++){
//					System.out.print(mat[i][j]);
//					System.out.print(" ");
//				}
//				System.out.println();
//			}
//			
//			//call Search function
//			if(!isInvalidInput){
//				Search(M,N);
//			}
//			else{
//				System.out.println("Results:");
//				System.out.println("Invalid Matrix");
//				System.out.println();
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
}
