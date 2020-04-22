package com.Project0.companies;
//***************************************************************************//
//Vanilla Java POJO
//**************************************************************************//



public class StockCompanies
{
    //Will be used to build specific companies portfolios for their actual stock shares.
    //These will be tracked by Stock Market, for # of shares. Share information is held elsewhere
    //This is simply needed for interfacing w/ user for heightened readability.
    private String name;
    private Integer companyID;

    //Standard Contructioneer
    //**************************************************************************//
    public StockCompanies(String[] arguments)
    {   //Takes a [0,1] array of String, making casting handle happen here, not elsewhere for comfort on incoming sql objects.
        this.name = arguments[0];
        try
        {
            this.companyID = new Integer(arguments[1]);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    //Standard Getters and Stters for the 2 fields. Shouldn't be used very often, aside form shakes.
    //**************************************************************************//
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
    //**************************************************************************//

    //Very simple toString.
    @Override
    public String toString()
    {
        return name + ", companyID= " + companyID;
    }
}
