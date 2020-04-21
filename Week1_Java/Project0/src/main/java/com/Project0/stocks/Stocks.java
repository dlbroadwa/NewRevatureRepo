package com.Project0.stocks;

import org.omg.CORBA.INTERNAL;

import java.util.HashMap;

public class Stocks
{
    private Integer companyID;
    private Integer totalShares;
    private Integer tradeableShares;
    private Float cost;

    public Stocks(){}

    public Stocks( String[] arguments)
    {
        try
        {

            this.companyID = new Integer(arguments[0]);
            this.totalShares = new Integer(arguments[1]);
            this.tradeableShares = new Integer(arguments[2]);
            this.cost = new Float(arguments[3]);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public Integer getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(Integer totalShares) {
        this.totalShares = totalShares;
    }

    public Integer getTradeableShares() {
        return tradeableShares;
    }

    public void setTradeableShares(Integer tradeableShares) {
        this.tradeableShares = tradeableShares;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  "Stocks with " + companyID +
                ": With total shares of " + totalShares +
                ", and Shares up for trade at " + tradeableShares +
                ", at $" + cost + "  per share";
    }
}

