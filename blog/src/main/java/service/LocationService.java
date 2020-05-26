package service;

import model.Location;

/**
 * LocationService interface to be implemented
 */
public interface LocationService {
	void createLocation(String name, String street_number, String route, String city, String state, String zipcode, String country, float latitude, float longitude);
	Location getLocation(String name, String street_number, String route, String city, String state);
}
