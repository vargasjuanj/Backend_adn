package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Stats implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int count_mutant_dna;
	@NotNull
	private int count_human_dna;
	@NotNull
	private double ratio;
	
	public Stats() {
		
	}
	public Stats(int count_mutant_dna, int count_human_dna, double ratio) {
		this.count_human_dna=count_human_dna;
		this.count_mutant_dna=count_mutant_dna;
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
