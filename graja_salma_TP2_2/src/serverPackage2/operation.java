package serverPackage2;

import java.io.Serializable;

public class operation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int op1;
	private int op2;
	private int resultat;
	private char operation;
	public operation(int op1, int op2, char operation) {
		this.op1 = op1;
		this.op2 = op2;
		
		this.operation = operation;
	}
	public int getOp1() {
		return op1;
	}
	public void setOp1(int op1) {
		this.op1 = op1;
	}
	public int getOp2() {
		return op2;
	}
	public void setOp2(int op2) {
		this.op2 = op2;
	}
	public int getResultat() {
		return resultat;
	}
	public void setResultat(int resultat) {
		this.resultat = resultat;
	}
	public char getOperation() {
		return operation;
	}
	public void setOperation(char operation) {
		this.operation = operation;
	}

}
