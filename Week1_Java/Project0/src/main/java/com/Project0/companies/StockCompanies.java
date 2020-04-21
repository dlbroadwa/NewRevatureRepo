package com.Project0.companies;
//***************************************************************************//

import com.Project0.brokers.StockBrokerRepository;
import com.Project0.utilities.PostgresConnectionUtilities;

import java.util.HashMap;

//**************************************************************************//



public class StockCompanies
    //Will be used to build specific companies portfolios for their actual stock shares.
    //These will be tracked by Stock Market, for # of shares.
{
    private String name;
    private Integer companyID;

    public StockCompanies(String[] arguments)
    {
        this.name = arguments[0];
        try
        {
            this.companyID = new Integer(arguments[1]);
        }
        catch(Exception e)
        {
            System.out.println("Cannot create company");
        }
        //(new StockCompaniesRepository(new PostgresConnectionUtilities())).update(this, this.companyID);
    }


    public void setCompanyID(Integer companyID)
    {
        this.companyID = companyID;
    }


    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public Integer getId()
    {
        return this.companyID;
    }
    public void setId(Integer id)
    {
        this.companyID = id;
    }

    @Override
    public String toString()
    {
        return "StockCompanies{" +
                "name=' " + name + '\'' +
                ", companyID= " + companyID +
                '}';
    }
}
