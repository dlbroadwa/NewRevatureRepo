package clients;

import data.Repo;
import models.GrossPay;

import java.util.List;

public class GrossPayService {
    private Repo<GrossPay, Integer> gpRepo;

    public GrossPayService(Repo<GrossPay, Integer> gpRepo) {
        this.gpRepo = gpRepo;
    }

    public List<GrossPay> getAllGrossPay() {
        return this.gpRepo.findAll();
    }
}
