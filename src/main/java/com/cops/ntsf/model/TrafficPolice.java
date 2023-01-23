package com.cops.ntsf.model;

import com.cops.ntsf.dao.PolicemanDAO;

public class TrafficPolice extends Policeman {
    public TrafficPolice(String name, String police_id, String nic, String rank, String police_station) {
        super(name, police_id, nic, rank, police_station);
    }

    public void setTrafficPoliceInfo() {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.insertTrafficPoliceInfo(this);

    }
}
