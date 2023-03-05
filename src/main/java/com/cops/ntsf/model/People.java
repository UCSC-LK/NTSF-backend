package com.cops.ntsf.model;

import com.cops.ntsf.dao.PeopleDAO;

public class People {

    public People(String nic) {
    }

    public void getCivilInfo() {
        PeopleDAO peopleDAO = new PeopleDAO();
        peopleDAO.fetchCivilInfo(this);
    }
}
