package com.project2_yyan_kpdavison;

public class featureAlgorithms {

	/**
	 * Our Heuristic from Project 1. If a chain of pieces passes a certain threshold
	 * the length of the chain is added to the value
	 * @return value of the boardstate
	 */
	public static int feature1() {
		int result = 0;
		int threshold = 2;
		
		int i,j;
		
		//vertical (column) Check for chains
		for(i = 0; i < IO_Main.numcolumns; i++){
			int count = 0;
			for(j = IO_Main.numrows - 1; j >= 0; j--){
				if(IO_Main.gameboard[j][i] == 0) continue;
				if(count != 0 && IO_Main.gameboard[j][i] / count < 0){
					if(Math.abs(count) >= threshold) result += count;
					count = 0;
				}
				count += IO_Main.gameboard[j][i];
			}
			if(Math.abs(count) >= threshold) result += count;
		}
		
		//Horizontal (row) Check
		for(i = 0; i < IO_Main.numrows; i++){
			int count = IO_Main.gameboard[i][0];
			int player = IO_Main.gameboard[i][0];
			for(j = 1; j <= IO_Main.numcolumns - 1; j++){
				if(IO_Main.gameboard[i][j] == 0) {
					if(Math.abs(count) >= threshold) result += count;
					count = 0;
					player = 0;
					continue;
				}
				else if(IO_Main.gameboard[i][j] == player) count = count + IO_Main.gameboard[i][j];
				else if(IO_Main.gameboard[i][j] != player) {
					if(Math.abs(count) >= threshold) result += count;
					player = IO_Main.gameboard[i][j];
					count = IO_Main.gameboard[i][j];
				}
			}
			if(Math.abs(count) >= threshold) result += count;
		}
		
		//Diagonal (\) check
		for(i=0;i<=IO_Main.numcolumns-1;i++){
			int x = i, y = IO_Main.numrows - 1;
			int count = 0;
			while(x < IO_Main.numcolumns && y >= 0){
				if(IO_Main.gameboard[y][x] == 0) {
					if(Math.abs(count) >= threshold) result += count;
					count = 0;
				}
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else {
					if(Math.abs(count) >= threshold) result += count;
					count = IO_Main.gameboard[y][x];
				}
				x++;
				y--;
			}
			if(Math.abs(count) >= threshold) result += count;
			
		}
		for(i = 0; i < IO_Main.numrows - 1; i++){
			int y = i, x = 0;
			int count = 0;
			while(x < IO_Main.numcolumns && y >= 0){
				if(IO_Main.gameboard[y][x] == 0) {
					if(Math.abs(count) >= threshold) result += count;
					count = 0;
				}
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else {
					if(Math.abs(count) >= threshold) result += count;
					count = IO_Main.gameboard[y][x];
				}
				x++;
				y--;
			}
			if(Math.abs(count) >= threshold) result += count;
		}
		
		//diagonal (/) check
		for(i=0;i<=IO_Main.numcolumns-1;i++){
			int x = i, y = 0;
			int count = 0;
			while(x < IO_Main.numcolumns && y < IO_Main.numrows){
				if(IO_Main.gameboard[y][x] == 0) {
					if(Math.abs(count) >= threshold) result += count;
					count = 0;
				}
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else {
					if(Math.abs(count) >= threshold) result += count;
					count = IO_Main.gameboard[y][x];
				}
				x++;
				y++;
			}
			if(Math.abs(count) >= threshold) result += count;
		}
		for(i = 1; i < IO_Main.numrows; i++){
			int y = i, x = 0;
			int count = 0;
			while(x < IO_Main.numcolumns && y < IO_Main.numrows){
				if(IO_Main.gameboard[y][x] == 0) {
					if(Math.abs(count) >= threshold) result += count;
					count = 0;
				}
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else {
					if(Math.abs(count) >= threshold) result += count;
					count = IO_Main.gameboard[y][x];
				}
				x++;
				y++;
			}
			if(Math.abs(count) >= threshold) result += count;
		}
		
		return result;
	}
	
	/**
	 * Player1's connect2 (2^2) + Player1's connect3 (3^2)... - Player2's connect2 - Player2's connect3...
	 * @return estimated value of boardstate
	 */
	public static int feature2() {
		int result = 0;
		
		int i,j;
		
		//vertical (column) Check for chains
		for(i = 0; i < IO_Main.numcolumns; i++){
			int count = 0;
			for(j = IO_Main.numrows - 1; j >= 0; j--){
				if(IO_Main.gameboard[j][i] == 0) continue;
				if(count != 0 && IO_Main.gameboard[j][i] / count < 0){
					// if different sign found
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = 0;
				}
				count += IO_Main.gameboard[j][i];
			}
			if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
		}
		
		//Horizontal (row) Check
		for(i = 0; i < IO_Main.numrows; i++){
			int count = IO_Main.gameboard[i][0];
			int player = IO_Main.gameboard[i][0];
			for(j = 1; j <= IO_Main.numcolumns - 1; j++){
				if(IO_Main.gameboard[i][j] == 0) {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = 0;
					player = 0;
					continue;
				}
				else if(IO_Main.gameboard[i][j] == player) count = count + IO_Main.gameboard[i][j];
				else if(IO_Main.gameboard[i][j] != player) {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					player = IO_Main.gameboard[i][j];
					count = IO_Main.gameboard[i][j];
				}
			}
			if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
		}
		
		//Diagonal (\) check
		for(i=0;i<=IO_Main.numcolumns-1;i++){
			int x = i, y = IO_Main.numrows - 1;
			int count = 0;
			while(x < IO_Main.numcolumns && y >= 0){
				if(IO_Main.gameboard[y][x] == 0) {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = 0;
				}
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = IO_Main.gameboard[y][x];
				}
				x++;
				y--;
			}
			if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
			
		}
		for(i = 0; i < IO_Main.numrows - 1; i++){
			int y = i, x = 0;
			int count = 0;
			while(x < IO_Main.numcolumns && y >= 0){
				if(IO_Main.gameboard[y][x] == 0) {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = 0;
				}
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = IO_Main.gameboard[y][x];
				}
				x++;
				y--;
			}
			if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
		}
		
		//diagonal (/) check
		for(i=0;i<=IO_Main.numcolumns-1;i++){
			int x = i, y = 0;
			int count = 0;
			while(x < IO_Main.numcolumns && y < IO_Main.numrows){
				if(IO_Main.gameboard[y][x] == 0) {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = 0;
				}
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = IO_Main.gameboard[y][x];
				}
				x++;
				y++;
			}
			if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
		}
		for(i = 1; i < IO_Main.numrows; i++){
			int y = i, x = 0;
			int count = 0;
			while(x < IO_Main.numcolumns && y < IO_Main.numrows){
				if(IO_Main.gameboard[y][x] == 0) {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = 0;
				}
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else {
					if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
					count = IO_Main.gameboard[y][x];
				}
				x++;
				y++;
			}
			if(Math.abs(count) > 1) result += count * count * (count / Math.abs(count));
		}
		
		return result;
	}

	/**
	 * for every space player1 owns if a surrounding space is also owned add 3, if empty add 2, if owned by player2 add 1. 
	 * for every space player2 owns if a surrounding space is also owned add -3, if empty add -2, if owned by player1 add -1
	 * @return value of heuristic estimate
	 */
	public static int feature3() {
		int result = 0;
		int i,j,k,n;
		
		//for each board location
		for(i=0;i<IO_Main.numcolumns;i++) {
			for(j=0;j<IO_Main.numrows; j++) {

				if(IO_Main.gameboard[j][i] == 1) {
					for(k=(i-1);k<=(i+1);k++) {
						for(n=(j-1);n<=(j+1);n++) {
							if(k >= 0 && k < IO_Main.numcolumns && n >= 0 && n < IO_Main.numrows) {
								if(IO_Main.gameboard[n][k] == (-1)) {
									result = result + 1;
								}
								else if(IO_Main.gameboard[n][k] == 1) {
									result = result + 3;
								}
								else {
									result = result + 2;
								}
							}
						}
					}
				}
				
				else if(IO_Main.gameboard[j][i] == -1) {
					for(k=(i-1);k<=(i+1);k++) {
						for(n=(j-1);n<=(j+1);n++) {
							if(k >= 0 && k < IO_Main.numcolumns && n >= 0 && n < IO_Main.numrows) {
								if(IO_Main.gameboard[n][k] == (-1)) {
									result = result - 3;
								}
								else if(IO_Main.gameboard[n][k] == 1) {
									result = result - 1;
								}
								else {
									result = result - 2;
								}
							}
						}
					}
				}
				
			}
		}
		
		return result;
	}
	
	/**
	 * Check for Gameover condition (player has connected 4)
	 * @return 1 if player1 wins, -1 if player 2 wins, 0 if it is a draw or there is no winner
	 */
	public static String feature4() {
		String result = "Unknown";
		boolean weWon = false;
		boolean weLost = false;
		
		int i,j;
		
		//vertical (column) Check
		for(i = 0; i < IO_Main.numcolumns; i++){
			int count = 0;
			for(j = IO_Main.numrows - 1; j >= 0; j--){ 
				//start checking from the top of the board, skip all empty spaces
				if(IO_Main.gameboard[j][i] == 0) continue;
				count += IO_Main.gameboard[j][i];
				//if a winning condition is found, break the loop as there shouldn't be multiple
				//winning conditions in a single column for both players
				if(count == 4) {
					weWon = true;
					break;
				}
				else if(count == (-1 * 4)) {
					weLost = true;
					break;
				}
			}
		}
		
		//Horizontal (row) Check
		for(i = 0; i < IO_Main.numrows; i++){
			int count = IO_Main.gameboard[i][0];
			int player = IO_Main.gameboard[i][0];
			for(j = 1; j <= IO_Main.numcolumns - 1; j++){
				//reset the count number and player if empty space is met
				if(IO_Main.gameboard[i][j] == 0) {
					count = 0;
					player = 0;
					continue;
				}
				//increment count number if consecutive discs from the same player is found
				else if(IO_Main.gameboard[i][j] == player) count = count + IO_Main.gameboard[i][j]; 
				//reset count number and player if the opponent's disc is found
				else if(IO_Main.gameboard[i][j] != player) {
					player = IO_Main.gameboard[i][j];
					count = IO_Main.gameboard[i][j];
				}
				
				//check for count number for each column and set winning/losing condition
				if(count == 4) weWon = true;
				else if(count == (-1 * 4)) weLost = true;
			}
		}
		
		//Diagonal (\) check
		//start from the top row from left to right
		for(i=0;i<=IO_Main.numcolumns-1;i++){
			int x = i, y = IO_Main.numrows - 1;
			int count = 0;
			//keep checking bottom right direction while inside the board
			while(x < IO_Main.numcolumns && y >= 0){
				if(IO_Main.gameboard[y][x] == 0) count = 0;	//reset count if empty
				else if(count == 0) count = IO_Main.gameboard[y][x]; //set count if a disc is found
				//increment count if consecutive discs are found
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x]; 
				else count = IO_Main.gameboard[y][x]; //update count if opponent's disc is found
				x++;
				y--;
				//check for winning/losing condition
				if(Math.abs(count) == 4) {
					if(count > 0) weWon = true;
					else if(count < 0) weLost = true;
				}
			}
			
		}
		//then start from the left column from up to down, skip the top-left space
		//same algorithm as above
		for(i = 0; i < IO_Main.numrows - 1; i++){
			int y = i, x = 0;
			int count = 0;
			while(x < IO_Main.numcolumns && y >= 0){
				if(IO_Main.gameboard[y][x] == 0) count = 0;
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else count = IO_Main.gameboard[y][x];
				x++;
				y--;
				
				if(Math.abs(count) == 4) {
					if(count > 0) weWon = true;
					else if(count < 0) weLost = true;
				}
			}
		}
		
		//diagonal (/) check
		//start from bottom column from left to right
		for(i=0;i<=IO_Main.numcolumns-1;i++){
			int x = i, y = 0;
			int count = 0;
			//keep checking upper right direction while inside the board
			while(x < IO_Main.numcolumns && y < IO_Main.numrows){
				if(IO_Main.gameboard[y][x] == 0) count = 0;	//reset count if empty
				else if(count == 0) count = IO_Main.gameboard[y][x]; //set count if a disc is found
				//increment count if consecutive discs are found
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else count = IO_Main.gameboard[y][x];//update count if opponent's dics is found
				x++;
				y++;
				//check for winning/losing condition
				if(Math.abs(count) == 4) {
					if(count > 0) weWon = true;
					else if(count < 0) weLost = true;
				}
			}
		}
		//then start from the left column from bottom to top, skip the bottom-left space
		//same algorithm as above
		for(i = 1; i < IO_Main.numrows; i++){
			int y = i, x = 0;
			int count = 0;
			while(x < IO_Main.numcolumns && y < IO_Main.numrows){
				if(IO_Main.gameboard[y][x] == 0) count = 0;
				else if(count == 0) count = IO_Main.gameboard[y][x];
				else if(IO_Main.gameboard[y][x] == (count / Math.abs(count))) count += IO_Main.gameboard[y][x];
				else count = IO_Main.gameboard[y][x];
				x++;
				y++;
				
				if(Math.abs(count) == 4) {
					if(count > 0) weWon = true;
					else if(count < 0) weLost = true;
				}
			}
		}
		
		//check for top row completely full, if woWon & weLost are still both false draw
		if(weWon == false && weLost == false) {
			boolean full = true;
			for(i=0;i<IO_Main.numcolumns;i++) {
				if(IO_Main.gameboard[IO_Main.numrows-1][i] == 0) {
					full = false;
					break;
				}
			}
			if(full) {
				weWon = true;
				weLost = true;
			}	
		}
		
		//if weWon && weLost draw
		if(weWon && weLost) result = "Draw";
		else if(weWon) result = "Player1";
		else if(weLost) result = "Player2";
		
		return result;
	}
	
	/**
	 * Each space is valued at 1, if a player owns the top of a column he owns all the spaces above him in that column, 
	 * he also gains value for every piece chained below him. If he owns the space directly to the left or right he gains 2 
	 * for owning that space, else if the space is empty 1 is gained.
	 * @return heuristic value
	 */
	public static int feature5() {
		int value = 0;
		int row,column,i,j = 0;
		
		for(column=0;column<IO_Main.numcolumns;column++) {
			for(row=(IO_Main.numrows - 1);row>0;row--) {
				if(IO_Main.gameboard[row][column] != 0) break;
			}
			
			//we have the top piece or column is empty
			if(IO_Main.gameboard[row][column] == 1) {
				value += (IO_Main.numrows - row);
			}
			else if(IO_Main.gameboard[row][column] == -1) {
				value -= (IO_Main.numrows - row);
			}
			
			if(row != 0) {
				//check for chain below
				for(i=(row-1);i>=0;i--) {
					if(IO_Main.gameboard[row][i] == IO_Main.gameboard[row][column]) value += IO_Main.gameboard[row][column];
					else break;
				}
			}
			
			//if space is owned check neighbors
			if(IO_Main.gameboard[row][column] != 0) {
				if(column > 0) {
					if(IO_Main.gameboard[row][column-1] == 0) value += IO_Main.gameboard[row][column];
					else if(IO_Main.gameboard[row][column-1] == IO_Main.gameboard[row][column]) {
						value += IO_Main.gameboard[row][column-1] + IO_Main.gameboard[row][column-1];
					}
				}
				if(column < (IO_Main.numcolumns-1)) {
					if(IO_Main.gameboard[row][column+1] == 0) value += IO_Main.gameboard[row][column];
					else if(IO_Main.gameboard[row][column+1] == IO_Main.gameboard[row][column]) {
						value += IO_Main.gameboard[row][column+1] + IO_Main.gameboard[row][column+1];
					}
				}
			}
		}
		
		return value;
	}
}
