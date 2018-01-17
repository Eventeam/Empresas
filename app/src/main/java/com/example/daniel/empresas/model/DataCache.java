package com.example.daniel.empresas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 04-01-2018.
 */

public class DataCache {
    private static List<Company> companyList;

    public static List<Company> getCompanyList() {
        if (null == companyList) companyList = new ArrayList<>();
        return companyList;
    }

    public static void setCompanyList(List<Company> companies) {
        companyList = companies;
    }
}
