package model;

/**
 * The Location model class, corresponding with the Location table in the database.
 */
public class Location {
	private int location_id;
	private String name;
	private String street_number;
	private String route;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private float latitude;
	private float longitude;

	/**
	 * Constructor
	 * @param name - String
	 * @param street_number - String
	 * @param route - String
	 * @param city - String
	 * @param state - String
	 * @param zipcode - String
	 * @param country - String
	 * @param latitude - float
	 * @param longitude - float
	 */
	public Location(String name, String street_number, String route, String city, String state, String zipcode, String country, float latitude, float longitude) {
		this.name = name;
		this.street_number = street_number;
		this.route = route;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.location_id = -1;
	}

	/**
	 * Constructor with super
	 * @param location_id - int
	 * @param name - String
	 * @param street_number - String
	 * @param route - String
	 * @param city - String
	 * @param state - String
	 * @param zipcode - String
	 * @param country - String
	 * @param latitude - float
	 * @param longitude - float
	 */
	public Location(int location_id, String name, String street_number, String route, String city, String state, String zipcode, String country, float latitude, float longitude) {
		this(name, street_number, route, city, state, zipcode, country, latitude, longitude);
		this.location_id = location_id;
	}

	public int getLocationId() {
		return location_id;
	}

	public String getName() {
		return name;
	}

	public String getStreetNumber() {
		return street_number;
	}

	public String getRoute() {
		return route;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getCountry() {
		return country;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}
}
