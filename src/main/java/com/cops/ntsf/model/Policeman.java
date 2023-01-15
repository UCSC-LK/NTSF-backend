package com.cops.ntsf.model;

import com.cops.ntsf.dao.PolicemanDAO;
<<<<<<< HEAD

public class Policeman {
    private String name;
    private String police_id;
    private String nic;

    private String rank;

=======

public class Policeman {
    private String name;
    private String police_id;
    private String nic;
    private String rank;
>>>>>>> 2a7290b7ab374a5bfccfbf55541222ae42d972d5
    private String police_station;

    public Policeman(String name, String police_id, String nic, String rank, String police_station)
    {
        this.name = name;
        this.police_id = police_id;
        this.nic = nic;
        this.rank = rank;
        this.police_station = police_station;
    }

    //getters
    public String getPolice_station() {
        return police_station;
    }

    public String getPolice_id() {
        return police_id;
    }

    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

    public String getRank() {
        return rank;
    }

    //setters

    public void setPolice_station(String police_station) {
        this.police_station = police_station;
    }

    public void setPolice_id(String police_id) {
        this.police_id = police_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void policemanAdded()
    {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.insert(this);
    }

}

