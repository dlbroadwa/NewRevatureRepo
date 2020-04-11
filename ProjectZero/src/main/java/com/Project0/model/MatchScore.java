package com.Project0.model;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;

public class MatchScore {
	
	private int[] scores;
	private int scoreTotal;
	private LocalDate dayPlayed;
	
	public MatchScore() {
		scores = new int[9];
		scoreTotal = 0;
		dayPlayed = LocalDate.now();
	}
	public MatchScore(int[] scores, int scoreTotal, LocalDate dayPlayed) {
		super();
		this.scores = scores;
		this.scoreTotal = scoreTotal;
		this.dayPlayed = dayPlayed;
	}
	
	public int[] getScores() {
		return scores;
	}
	public void setScores(int[] scores) {
		this.scores = scores;
	}
	public int getScoreTotal() {
		for(int e : scores){
			scoreTotal += e;
		}
		return scoreTotal;
	}
	public void setScoreTotal(int scoreTotal) {
		this.scoreTotal = getScoreTotal();
	}
	public LocalDate getDayPlayed() {
		return dayPlayed;
	}
	public void setDayPlayed(LocalDate dayPlayed) {
		this.dayPlayed = dayPlayed;
	}
	
	@Override
	public String toString() {
		return "MatchScore [scores=" + Arrays.toString(scores) + ", scoreTotal=" + getScoreTotal() + ", dayPlayed="
				+ dayPlayed + "]";
	}
}
