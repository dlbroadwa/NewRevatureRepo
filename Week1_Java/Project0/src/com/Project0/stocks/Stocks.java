package com.Project0.stocks;

import org.omg.CORBA.INTERNAL;

public class Stocks
{


    private class Stock
    {
        private Integer companyID;
        private Integer totalShares;
        private Integer tradeableShares;
        private Stock Stock(String[] arguments)
        {
            try
            {
                this.companyID = new Integer(arguments[0]);
                this.totalShares = new Integer(arguments[1]);
                this.tradeableShares = new Integer(arguments[2]);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return this;

        }
    }
}
