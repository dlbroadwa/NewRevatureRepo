package com.Project0.companies;

import java.util.HashMap;
import com.Project0.companies.StockCompanies;

public class StockCompaniesManager
{
    public static HashMap<Integer, StockCompanies> getAllCompanies()

    {
        return StockCompanies.getCompaniesMap();
    }

    public static String getNameByID(Integer id)
    {
        return StockCompanies.getStockCompanyNameByID(id);
    }

    public static StockCompanies makeCompany(String[] a)
    {
        return new StockCompanies(a);
    }
    public static String printCompanyByID(Integer i)
    {
        return StockCompanies.getCompaniesMap().get(i).getName();
    }
}
