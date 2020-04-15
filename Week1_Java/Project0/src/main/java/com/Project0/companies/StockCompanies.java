package com.Project0.companies;
//***************************************************************************//

import java.util.HashMap;

//**************************************************************************//



public class StockCompanies
    //Will be used to build specific companies portfolios for their actual stock shares.
    //These will be tracked by Stock Market, for # of shares.
{
    private static HashMap<Integer,StockCompanies> companiesMap = new HashMap<Integer, StockCompanies>();
    private String name;
    private Integer companyID;
    private boolean tradeable = false;

    protected StockCompanies(String[] arguments)
    {
        this.name = arguments[0];
        try
        {
            this.companyID = new Integer(arguments[1]);
            this.tradeable = true;
            companiesMap.put(this.companyID, this);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected static HashMap<Integer, StockCompanies> getCompaniesMap()
    {
        return companiesMap;
    }

    protected static String getStockCompanyNameByID(Integer id)
    {
        return (companiesMap.get(id)).getName();
    }

    protected void setCompanyID(Integer companyID)
    {
        this.companyID = companyID;
    }

    protected boolean isTradable()
    {
        return tradeable;
    }

    protected void setTradeble(boolean tradeable)
    {
        this.tradeable = tradeable;
    }

    protected String getName()
    {
        return this.name;
    }

    protected void setName(String name)
    {
        this.name = name;
    }
}
