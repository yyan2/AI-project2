package com.project2_yyan_kpdavison;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO_Main {

	static BufferedReader input = null;
	static FileWriter fw = null;
	static FileReader fr = null;
	static int[][] gameboard = new int[6][7]; //[row][column]
	static int numcolumns = 7;
	static int numrows = 6;
	
	public static void main(String[] args) throws IOException{
		
		if(args.length != 2) {
			System.out.println("arguments: infile.txt outfile.csv");
			return;
		}
		
		fw = new FileWriter(new File(args[1]));
		fr = new FileReader(new File(args[0]));
		input = new BufferedReader(fr);
		
		while(true) {
			String line = input.readLine();
			if(line == null) break;
			String[] boardstate = line.split(",");
			if(boardstate.length != 43) {
				System.out.println("Error, file did not give enough input on a line");
				break;
			}

			int place = 0;
			int row, column = 0;
			
			for(column=0;column<7;column++) {
				for(row=0;row<6;row++) {
					int space = Integer.parseInt(boardstate[place]);
					if(space == 2) gameboard[row][column] = -1;
					else gameboard[row][column] = space;
					place++;
				}
			}
			
			int feature1 = featureAlgorithms.feature1();
			int feature2 = featureAlgorithms.feature2();
			int feature3 = featureAlgorithms.feature3();
			String feature4 = featureAlgorithms.feature4();
			int feature5 = featureAlgorithms.feature5();
			
			String outstring = line + "," + feature1 + "," + feature2 + "," + feature3 + "," + feature4 + "," + feature5;
			fw.write(outstring + "\r\n");
			fw.flush();
		};
		
		input.close();
		fr.close();
		fw.close();
	}

}
