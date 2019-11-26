package com.example.demo.dto;

import java.io.Serializable;

public class StatsDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private int count_mutant_dna;
private int count_human_dna;
private double ratio;
public StatsDTO() {
	super();
}
public StatsDTO(int count_mutant_dna, int count_human_dna, double ratio) {
	super();

	this.count_mutant_dna = count_mutant_dna;
	this.count_human_dna = count_human_dna;
	this.ratio=ratio;
}

public int getCount_mutant_dna() {
	return count_mutant_dna;
}
public void setCount_mutant_dna(int count_mutant_dna) {
	this.count_mutant_dna = count_mutant_dna;
}
public int getCount_human_dna() {
	return count_human_dna;
}
public void setCount_human_dna(int count_human_dna) {
	this.count_human_dna = count_human_dna;
}
public double getRatio() {
	return ratio;
}
public void setRatio(double ratio) {
	this.ratio = ratio;
}

}
