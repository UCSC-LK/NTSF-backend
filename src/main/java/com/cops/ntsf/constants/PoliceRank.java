package com.cops.ntsf.constants;

public enum PoliceRank {
    IGP;
    OIC,
    INVESTIGATION_OFFICER,
    COURT_SERGEANT,
    TRAFFIC_POLICE;

    public static PoliceRank fromId(int policeRank) {
        return PoliceRank.values()[policeRank - 1];
    }
}
