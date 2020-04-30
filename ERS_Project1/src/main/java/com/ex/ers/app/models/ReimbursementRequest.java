package com.ex.ers.app.models;

public class ReimbursementRequest {
    private boolean pending;
    private boolean approved;
    private Employee requester;
    private Manager approver;
    private float amount;
    private String comment;

    public ReimbursementRequest(boolean pending, boolean approved, Employee requester, Manager approver, float amount, String comment) {
        this.pending = pending;
        this.approved = approved;
        this.requester = requester;
        this.approver = approver;
        this.amount = amount;
        this.comment = comment;
    }

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

    public Employee getRequester() {
        return requester;
    }

    public void setRequester(Employee requester) {
        this.requester = requester;
    }

    public Manager getApprover() {
        return approver;
    }

    public void setApprover(Manager approver) {
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
