package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cell")
public class Cell {


	@Param(0)
	public int row;
	@Param(1)
	public int column;
	@Param(2)
	public int colore;
	
	public Cell(int r,int c,int col){
		this.row=r;
		this.column=c;
		this.colore= col;
	}
	
	public Cell() {
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getColore() {
		return colore;
	}

	public void setColore(int value) {
		this.colore = value;
	}

}
