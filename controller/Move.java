package controller;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("move")
public class Move {
	
	@Param(0)
	public int id;
	@Param(1)
	public int row;
	@Param(2)
	public int col;
//	public int x;
//	public int y;
	
	public Move(int id, int row, int col) {
		super();
		this.id = id;
		this.row = row;
		this.col = col;
		
//		if(id%10 < 0) {
//			 x = 0;
//			 y= id;
//		}
//		else {
//			y = id%10;
//			x = id/10;
//		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
//	public int getX() {
//		return x;
//	}
//	
//	public int getY() {
//		return y;
//	}

}
