package com.ex.ers.models;

public class ReimbursementRequest {
    private boolean pending;
    private boolean approved;
    private Person requester;
    private Person approver;
    private float amount;
    private String comment;

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

    public Person getRequester() {
        return requester;
    }

    public void setRequester(Person requester) {
        this.requester = requester;
    }

    public Person getApprover() {
        return approver;
    }

    public void setApprover(Person approver) {
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
}
