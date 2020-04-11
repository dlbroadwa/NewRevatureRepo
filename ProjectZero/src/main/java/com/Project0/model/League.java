package com.Project0.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class League {
	private String name;
	private LocalDate playDay;
	private int weeksDuration;
	private ArrayList<Golfer> golfers;

	public League() {
		name = "";
		playDay = LocalDate.now();
		weeksDuration = 0;
		golfers = new ArrayList<Golfer>();
	}
	
	public League(String name, LocalDate playDay, int weeksDuration, ArrayList<Golfer> golfers) {
		super();
		this.name = name;
		this.playDay = playDay;
		this.weeksDuration = weeksDuration;
		this.golfers = golfers;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getPlayDay() {
		return playDay;
	}
	public void setPlayDay(LocalDate playDay) {
		this.playDay = playDay;
	}
	public int getWeeksDuration() {
		return weeksDuration;
	}

	public void setWeeksDuration(int weeksDuration) {
		this.weeksDuration = weeksDuration;
	}
	public ArrayList<Golfer> getGolfers() {
		return golfers;
	}
	public void setGolfers(ArrayList<Golfer> golfers) {
		this.golfers = golfers;
	}
}
