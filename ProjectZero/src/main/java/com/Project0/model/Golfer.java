package com.Project0.model;

import java.util.ArrayList;

public class Golfer {

	private long userID;
	private String name;
	private String address;
	//10 char field and strip out all non-numeric data
	private String phone;
	//10 char field and strip out all non-numeric data
	private String emergencyPhone;
	private String carMake;
	private String carModel;
	private String carLicensePlate;
	
	//What league this golfer belongs to
	private League league;
	
	//The list of scores for this golfer
	private ArrayList<MatchScore> scores;
	

	public Golfer() {

	}

	public Golfer(long userID, String name, String address, String phone, String emergencyPhone, String carMake, String carModel, String carLicensePlate) {
		this.userID = userID;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.emergencyPhone = emergencyPhone;
		this.carMake = carMake;
		this.carModel = carModel;
		this.carLicensePlate = carLicensePlate;
	}

	@Override
	public String toString() {
		return "Golfer{" +
				"userID=" + userID +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				", emergencyPhone='" + emergencyPhone + '\'' +
				", carMake='" + carMake + '\'' +
				", carModel='" + carModel + '\'' +
				", carLicensePlate ='" + carLicensePlate + '\'' +
				'}';
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarLicensePlate() {
		return carLicensePlate;
	}

	public void setCarLicensePlate(String carLicensePlate) {
		this.carLicensePlate = carLicensePlate;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public ArrayList<MatchScore> getScores() {
		return scores;
	}

	public void setScores(ArrayList<MatchScore> scores) {
		this.scores = scores;
	}
}

