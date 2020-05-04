package com.ex.ers.services;

import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.DAO.ReimbursementDAO;

public class ReimbursementService {
    private ReimbursementDAO reimbursementDAO;
    public ReimbursementService(){this.reimbursementDAO = new ReimbursementDAO();}
    public ReimbursementService(ReimbursementDAO reimbursementDAO){
        this.reimbursementDAO = reimbursementDAO;
    }




}
