package com.cops.ntsf.model;

import com.cops.ntsf.dao.PedestrianDAO;

public class Pedestrian extends User {

    public Pedestrian(String userId, String nic) {
        super(userId);
        this.setNic(nic);
    }

    public Pedestrian() {
        super();
    }

    public void getPedestrianFromNic() {
        PedestrianDAO pedestrianDAO = new PedestrianDAO();
        pedestrianDAO.getPedestrianFromNic(this);
    }

//    public String getNic() {
//        return nic;
//    }

//    public void setNic(String nic) {
//        this.nic = nic;
//    }

    public void setPedestrianInfo() {
        PedestrianDAO pedestrianDAO = new PedestrianDAO();
        pedestrianDAO.insertPedestrianInfo(this);
    }
}
