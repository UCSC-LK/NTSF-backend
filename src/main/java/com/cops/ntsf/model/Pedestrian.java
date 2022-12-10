package com.cops.ntsf.model;

import com.cops.ntsf.dao.PedestrianDAO;

public class Pedestrian extends User {

    private String nic;

    public void getPedestrianFromNic() {
        PedestrianDAO pedestrianDAO = new PedestrianDAO();
        pedestrianDAO.getPedestrianFromNic(this);
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
