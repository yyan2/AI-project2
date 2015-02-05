package com.project2_yyan_kpdavison;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class IO_Main {

	static BufferedReader input = null;
	static FileWriter fw = null;
	static FileReader fr = null;
	int[][] gameboard = new int[6][7]; //[row][column]
	
	public static void main(String[] args) throws IOException{
		if(args.length != 3) {
			System.out.println("arguments: infile.txt outfile.csv");
			return;
		}
		
		fw = new FileWriter(new File(args[2]));
		fr = new FileReader(new File(args[1]));
		input = new BufferedReader(fr);
		
		while(true) {
			String line = input.readLine();
			if(line == null) break;
			String[] boardstate = line.split(",");
			if(boardstate.length != 43) {
				System.out.println("Error, file did not give enough input on a line");
				break;
			}

			int[][] board = new int[6][7]; //[row][column]
			int place = 0;
			int predicted_winner, row, column = 0;
			
			for(column=0;column<7;column++) {
				for(row=0;row<6;row++) {
					board[row][column] = Integer.parseInt(boardstate[place]);
					place++;
				}
			}
			predicted_winner = Integer.parseInt(boardstate[place]);
			
			int feature1 = featureAlgorithms.feature1(board);
			int feature2 = featureAlgorithms.feature2(board);
			int feature3 = featureAlgorithms.feature3(board);
			int feature4 = featureAlgorithms.feature4(board);
			int feature5 = featureAlgorithms.feature5(board);
			
			String outstring = line + "," + feature1 + "," + feature2 + "," + feature3 + "," + feature4 + "," + feature5;
			fw.write(outstring + "\r\n");
			fw.flush();
		};
		
		input.close();
		fr.close();
		fw.close();
	}

}
