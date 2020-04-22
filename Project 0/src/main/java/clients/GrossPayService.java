package clients;

import data.Dao;
import models.GrossPay;

import java.util.List;

/**
 * Gross Pay service that utilizes the grosspay dao to help perform search, update, add, and delete functions in the GrossPay SQL DAO
 * This service is not being used at this time.
 */
public class GrossPayService {
    private Dao<GrossPay, String> gpDao;

    public GrossPayService(Dao<GrossPay, String> gpDao) {
        this.gpDao = gpDao;
    }

    public List<GrossPay> getAllGrossPay() {
        return (List<GrossPay>) this.gpDao.findAll();
    }
}
