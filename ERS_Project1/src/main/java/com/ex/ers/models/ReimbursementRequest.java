package com.ex.ers.models;

import com.google.gson.JsonObject;

public class ReimbursementRequest {
    private boolean pending;
    private boolean approved;
    private JsonObject requester;
    private String approver;
    private float amount;
    private String comment;
    private int id;
    private int requestorid;

    public void setRequestorid(int requestorid){
        this.requestorid=requestorid;
    }

    public int getRequestorid(int requestorid){
        return this.requestorid;
    }

    public ReimbursementRequest() {}

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public JsonObject getRequester() {
        return requester;
    }

    public void setRequester(JsonObject requester) {
        this.requester = requester;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
