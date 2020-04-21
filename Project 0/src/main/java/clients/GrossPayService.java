package clients;

import data.Dao;
import models.GrossPay;

import java.util.List;

public class GrossPayService {
    private Dao<GrossPay, Integer> gpDao;

    public GrossPayService(Dao<GrossPay, Integer> gpDao) {
        this.gpDao = gpDao;
    }

    public List<GrossPay> getAllGrossPay() {
        return (List<GrossPay>) this.gpDao.findAll();
    }
}
