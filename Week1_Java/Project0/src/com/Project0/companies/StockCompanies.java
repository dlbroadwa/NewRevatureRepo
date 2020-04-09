package com.Project0.companies;
//***************************************************************************//
//imports//


//**************************************************************************//

public class StockCompanies
    //Will be used to build specific companies portfolios for their actual stock shares.
    //These will be tracked by Stock Market, for # of shares.
{
    private class Company
    {
        private String name;
        private Integer companyID;
        private boolean tradeable = false;

        private Company Company (String[] arguments)
        {
            this.name = arguments[0];
            try
            {
                this.companyID = new Integer(arguments[1]);
                this.tradeable = true;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return this;
        }

    }
}
