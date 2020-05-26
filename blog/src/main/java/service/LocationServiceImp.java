package service;

import data.LocationRepository;
import model.Location;

/**
 * Implementation of the LocationService interface. Handles the logic between servlets and LocationRepository DAO.
 */
public class LocationServiceImp implements LocationService {
	private final LocationRepository locationRepository;

	/**
	 * Constructor taking in a LocationRepository object
	 * @param locationRepository LocationRepository object instantiated and passed by PostContextListener class
	 */
	public LocationServiceImp(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	/**
	 * Creates a Location objects from arguments passed in by the servlet and calls LocationRepository to write the Location object to the database.
	 * @param name String for name of the location
	 * @param street_number String for street number of the location
	 * @param route String for route of the location
	 * @param city String for the city of the location
	 * @param state String for the state abbreviation of the location
	 * @param zipcode String for the zipcode of the location
	 * @param country String for the county of the location
	 * @param latitude float for the latitude of the location
	 * @param longitude float for the longitude of the location
	 */
	public void createLocation(String name, String street_number, String route, String city, String state, String zipcode, String country, float latitude, float longitude) {
		Location location = new Location(name, street_number, route, city, state, zipcode, country, latitude, longitude);
		if (locationRepository.findLocation(name, street_number, route, city, state) == null)
			locationRepository.create(location);
	}

	/**
	 * Calls LocationRepository to query the database to find a Location by the name, street number, route, city, and state.
	 * @param name String for location's name
	 * @param street_number String for location's street number
	 * @param route String for location's route
	 * @param city String for location's city
	 * @param state String for location's state abbreviation
	 * @return returns the found Location object
	 */
	public Location getLocation(String name, String street_number, String route, String city, String state) {
		Location location = locationRepository.findLocation(name, street_number, route, city, state);
		return location;
	}
}
