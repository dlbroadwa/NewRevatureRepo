package com.ex.ers.services;

import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.DAO.ReimbursementDAO;
import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;

public class ReimbursementService {
    private ReimbursementDAO reimbursementDAO;
    public ReimbursementService(){this.reimbursementDAO = new ReimbursementDAO();}
    public ReimbursementService(ReimbursementDAO reimbursementDAO){
        this.reimbursementDAO = reimbursementDAO;
    }

    public ReimbursementRequest saveNewReimReq (Person requester, float amount, String comment){
        ReimbursementRequest reimbursementRequest = new ReimbursementRequest();
        reimbursementRequest.setRequester(requester);
        reimbursementRequest.setAmount(amount);
        reimbursementRequest.setComment(comment);
        this.reimbursementDAO.save(reimbursementRequest);

        return reimbursementRequest;
    }



}
