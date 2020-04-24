package com.Project0.model;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;

public class MatchScore {

	private Golfer owningGolfer = null;
	private int score;
	private LocalDate dayPlayed;
	
	public MatchScore() {
		owningGolfer = null;
		score = 0;
		dayPlayed = LocalDate.now();
	}
	public MatchScore(Golfer owningGolfer, int score, LocalDate dayPlayed) {
		super();
		this.owningGolfer = owningGolfer;
		this.score = score;
		this.dayPlayed = dayPlayed;
	}

	public Golfer getOwningGolfer() {
		return owningGolfer;
	}
	public void setOwningGolfer(Golfer owningGolfer) {
		this.owningGolfer = owningGolfer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public LocalDate getDayPlayed() {
		return dayPlayed;
	}
	public void setDayPlayed(LocalDate dayPlayed) {
		this.dayPlayed = dayPlayed;
	}

	@Override
	public String toString() {
		return "MatchScore{" +
				"owningGolfer=" + owningGolfer.getName() +
				", score=" + score +
				", dayPlayed=" + dayPlayed +
				'}';
	}
}
