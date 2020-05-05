package com.ex.ers.services;

import com.ex.ers.DAO.PersonDAO;
import com.ex.ers.DAO.ReimbursementDAO;
import com.ex.ers.models.Person;
import com.ex.ers.models.ReimbursementRequest;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {
    private ReimbursementDAO reimbursementDAO;
    public ReimbursementService(){this.reimbursementDAO = new ReimbursementDAO();}
    public ReimbursementService(ReimbursementDAO reimbursementDAO){
        this.reimbursementDAO = reimbursementDAO;
    }

    public ReimbursementRequest saveNewReimReq (JsonObject object, float amount, String comment){
        ReimbursementRequest reimbursementRequest = new ReimbursementRequest();
        reimbursementRequest.setRequester(object);
        reimbursementRequest.setAmount(amount);
        reimbursementRequest.setComment(comment);
        this.reimbursementDAO.save(reimbursementRequest);

        return reimbursementRequest;
    }

    public List<ReimbursementRequest> getAll(){
        List<ReimbursementRequest> all = new ArrayList();
        all = reimbursementDAO.findAll();
        return all;
    }


    public List<ReimbursementRequest> getAllForId(int id){
        List<ReimbursementRequest> all = new ArrayList();
        all = reimbursementDAO.findAllByID(id);
        return all;
    }

    public int markApproved(ReimbursementRequest reimbursementRequest, boolean approve, String managerName){
        int marked =0;
        ReimbursementRequest markedReim = new ReimbursementRequest();
        markedReim.setId(reimbursementRequest.getId());
        markedReim.setApprover(managerName);
        markedReim.setRequester(reimbursementRequest.getRequester());
        markedReim.setApproved(approve);
        markedReim.setAmount(reimbursementRequest.getAmount());
        markedReim.setComment(reimbursementRequest.getComment());

        marked = reimbursementDAO.update(markedReim);
        return marked;
    }
}
