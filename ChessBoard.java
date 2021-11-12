/* 
	X : Row Number as per Array
	Y : Column Number as per Array 

	Side (Left/Right) coding:  
			Note: Not Aplicable for Pawns 
		Left  : 0
		Right : 1

	Blocks Colour:
		Even : White
		Odd  : Black

	Pieces Colour:
		Black : 0;
		White : 1;
*/

public class ChessBoard{ // Start of Class

	private String board[][] = new String[8][8];
	private String recent;
	public ChessBoard(){ // Start Of Constructor

		int i,j;

		// Loops for Declaring Pawns & Non-Pieces Blocks
		for(i=0;i<8;i++){ // Start of Row For loop

			for(j=0;j<8;j++){ // Start of Column For loop

				if(i==1){
					board[i][j] = "wP"+j;
				}

				else if(i==6){
					board[i][j] = "bP"+j;
				}

				else {
					board[i][j] = "000";
				}

			} //End of Row For loop

		} //End of Column For loop

		// Placing Rookhs
		board[0][0] = "wR0";
		board[0][7] = "wR1";
		board[7][0] = "bR0";
		board[7][7] = "bR1";

		// Placing Horses
		board[0][1] = "wH0";
		board[0][6] = "wH1";
		board[7][1] = "bH0";
		board[7][6] = "bH1";

		// Placing Bishops
		board[0][2] = "wB0";
		board[0][5] = "wB1";
		board[7][2] = "bB0";
		board[7][5] = "bB1";

		// Placing Kings
		board[0][3] = "wK0";
		board[7][3] = "bK0";

		// Placing Queens
		board[0][4] = "wQ0";
		board[7][4] = "bQ0";


	}// End Of Constructor

	public void showBoard(){ //Start of showBoard()

		int i,j;
		for(i=0;i<8;i++){ 

			for(j=0;j<8;j++){ 

				if(board[i][j]=="000"){

					System.out.print("  " + "\t");
				}
				else{
					System.out.print(board[i][j] + "\t");
				}
			}

			System.out.println("\n");
		}
	}//End of showBoard()


	public boolean validMovePawn(int colour,int originX,int originY,int destX,int destY){//Start of validMovePawn()

		boolean flg = false;
		if(colour==0){

			if(board[originX-1][originY]=="000"){
				
				if(originX-1 == destX && originY == destY){
					flg = true;
				}
			}
			else if(originY!=7 && board[originX-1][originY+1]!="000"){
				
				if(originX-1 == destX && originY+1 == destY){
					flg = true;
				}
			}
			else if(originY!=0 && board[originX-1][originY-1]!="000"){
				
				if(originX-1 == destX && originY-1 == destY){
					flg = true;
				}
			}

			if(!flg && originX == 6 && originX - 2 == destX && originY == destY)
				flg = true;
		}

		else if(colour==1){

			if(board[originX+1][originY]=="000"){
				
				if(originX+1 == destX && originY == destY){
					flg = true;
				}
			}
			else if(originY!=7 && board[originX+1][originY+1]!="000"){
				
				if(originX+1 == destX && originY+1 == destY){
					flg = true;
				}

			}
			else if(originY!=0 && board[originX+1][originY-1]!="000"){
				
				if(originX+1 == destX && originY-1 == destY){
					flg = true;
				}
			}

			if(!flg && originX == 1 && originX + 2 == destX && originY == destY)
				flg = true;
		}

		
		return flg;

	}//End of validMovePawn()
	public boolean validMoveBishop(int colour, int originX,int originY,int destX,int destY){ //Start of validMoveBishop()

		int oX=originX, oY=originY;
		boolean flg=false;
		while(!flg && (oX!=0 && oY!=7)){

			if(board[oX-1][oY+1]=="000"){

				if(oX-1 == destX && oY+1 == destY){
					flg = true;
				}
				oX--;
				oY++;
			}
			else{
				if(oX-1 == destX && oY+1 == destY){
					flg = true;
				}
				break;
			}
		}
		oX=originX;
		oY=originY;
		while(!flg && (oX!=0 && oY!=0)){

			if(board[oX-1][oY-1]=="000"){

				if(oX-1 == destX && oY-1 == destY){
					flg = true;
				}
				oX--;
				oY--;
			}
			else{
				if(oX-1 == destX && oY-1 == destY){
					flg = true;
				}
				break;
			}
		}
		oX=originX;
		oY=originY;
		while(!flg && (oX!=7 && oY!=7)){ 

			if( board[oX+1][oY+1]=="000"){
		
				if(oX+1 == destX && oY+1 == destY){
					flg = true;
				}
				oX++;
				oY++;
			}
			else{
				
				if(oX+1 == destX && oY+1 == destY){
					flg = true;
				}
				break;
			}
		}
		oX=originX;
		oY=originY;
		while(!flg && (oX!=7 && oY!=0)){

			if(board[oX+1][oY-1]=="000"){

				if(oX+1 == destX && oY-1 == destY){
					flg = true;
				}

				oX++;
				oY--;
				
			}
			else{
				
				if(oX+1 == destX && oY-1 == destY){
					flg = true;
				}
				break;
			}
		}
		if(flg && colour==1 && board[destX][destY].charAt(0)=='w')
			flg=false;
		else if(flg && colour==0 && board[destX][destY].charAt(0)=='b')
			flg=false;
		return flg;
	}//End of validMoveBishop()

	public boolean validMoveRook(int colour, int originX,int originY,int destX,int destY){ //Start of validMoveRook()

		int oX=originX, oY=originY;
		boolean flg=false;

		if(oX == destX){

			while(!flg && oY!=7 && oY<destY){

				if(board[oX][oY+1] == "000"){
				
					if(++oY == destY){
						flg = true;
					}
					
				}
				else{
				
					if(oY+1 == destY){
						flg = true;
					}
					break;
				}

			}
			oX=originX;
			oY=originY;
			while(!flg && oY!=0 && oY>destY){

				if(board[oX][oY-1] == "000"){
				
					if(--oY == destY){
						flg = true;
					}
				}
				else{
				
					if(oY-1 == destY){
						flg = true;
					}
					break;
				}

			}
		}

		else if(oY == destY){

			oX=originX;
			oY=originY;
			while(!flg && oX!=0 && oX>destX){

				if(board[oX-1][oY] == "000"){
				
					if(--oX == destX){
						flg = true;
					}
				}
				else{
				
					if(oX-1 == destX){
						flg = true;
					}
					break;
				}

			}
			oX=originX;
			oY=originY;
			while(!flg && oX!=7 && oX<destX){

				if(board[oX+1][oY] == "000"){
				
					if(++oX == destX){
						flg = true;
					}
				}
				else{
				
					if(oX+1 == destX){
						flg = true;
					}
					break;
				}

			}
		}
		if(flg && colour==1 && board[destX][destY].charAt(0)=='w')
			flg=false;
		else if(flg && colour==0 && board[destX][destY].charAt(0)=='b')
			flg=false;
		return flg;
	} //End of validMoveRook()

	public boolean validMoveQueen(int colour, int originX,int originY,int destX,int destY){ //Start of validMoveQueen()

		if(validMoveRook(colour, originX, originY, destX, destY))
			return true;
		if(validMoveBishop(colour, originX, originY, destX, destY))
			return true;
		
		return false;
	}//End of validMoveQueen()

	public boolean validMoveKnight(int colour, int originX,int originY,int destX,int destY){ //Start of validMoveKnight()

		boolean flg=false;

		if(originX-1 == destX){
			if(originY-2 == destY)
				flg = true;
			else if (originY+2 == destY) 
				flg = true;
		}
		else if(originX-2 == destX){
			if (originY-1 == destY) 
				flg = true;
			else if (originY+1 == destY) 
				flg = true;
		}
		else if(originX+1 == destX){
			if(originY-2 == destY)
				flg = true;
			else if (originY+2 == destY) 
				flg = true;
		}
		else if(originX+2 == destX){
			if (originY-1 == destY) 
				flg = true;
			else if (originY+1 == destY) 
				flg = true;
		}

		if(flg && colour==1 && board[destX][destY].charAt(0)=='w')
			flg=false;
		else if(flg && colour==0 && board[destX][destY].charAt(0)=='b')
			flg=false;

		return flg;

	}//End of validMoveKnight()

	public boolean validMoveKing(int colour, int originX,int originY,int destX,int destY){ //Start of validMoveKing()

		boolean flg=false;
		if(originX - 1 == destX){

			if(originY - 1 == destY)
				flg = true;
			else if(originY == destY)
				flg = true;
			else if(originY + 1 == destY)
				flg = true;
		}
		else if(originX == destX){

			if(originY - 1 == destY)
				flg = true;
			else if(originY + 1 == destY)
				flg = true;

		}
		else if(originX + 1 == destX){

			if(originY - 1 == destY)
				flg = true;
			else if(originY == destY)
				flg = true;
			else if(originY + 1 == destY)
				flg = true;
		}

		if(flg && colour==1 && board[destX][destY].charAt(0)=='w')
			flg=false;
		else if(flg && colour==0 && board[destX][destY].charAt(0)=='b')
			flg=false;
		
		return flg;
	}//End of validMoveKing()

	public boolean isItValid(char piece,int colour, int originX,int originY,int destX,int destY){ // Start of isItValid()

		switch(piece){

			case 'B':
				return validMoveBishop(colour, originX, originY, destX, destY);
			case 'R':
				return validMoveRook(colour, originX, originY, destX, destY);
			case 'H':
				return validMoveKnight(colour, originX, originY, destX, destY);
			case 'Q':
				return (validMoveBishop(colour, originX, originY, destX, destY) || validMoveRook(colour, originX, originY, destX, destY));
			case 'P':
				return validMovePawn(colour, originX, originY, destX, destY);
			case 'K':
				return validMoveKing(colour, originX, originY, destX, destY);
			default:
				return false;

		}

	}// End of isItValid()

	public void changePieces(int originX,int originY,int destX,int destY){

		recent = board[destX][destY];
		board[destX][destY] = board[originX][originY];
		board[originX][originY] = "000";

	}

	public void undo(int originX, int originY, int destX, int destY){

		board[originX][originY] = board[destX][destY];
		board[destX][destY] = recent;

	}

	public String getPiece(int x,int y){

		return board[x][y];
	}

	public int getIndex(String piece){
		int j,i;
		for(i=0;i<8;i++){
			//j = Arrays.binarySearch(board[i], piece);
			for(j=0;j<8;j++){
				if (board[i][j].equals(piece)) {
					return (i*10)+j;
					
				}
			}
				
		}
		return -1;
	}

	public boolean checkForCheck(char colour){ //Start of checkForCheck()
		return true;
	}//End of checkForCheck()

	public boolean checkForCheckMate(char color){ //Start of checkForCheckMate()
		return true;
	}//End of checkForCheckMate()

	public String getRecent(){

		return recent;
	}

	public void reset(){
		recent = "000";
	}

} // End of Class