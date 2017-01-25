package org.tcb;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tcb.dao.HbaseDAO;

public enum SingletonVariablesShare {
    INSTANCE;

    private HbaseDAO hbaseDAO = new HbaseDAO();

//    private Map<String, Map<String, String>> atmGeoLoc = new HashMap<>();

//
//    public Map<String, Map<String, String>> getAtmGeoLoc() {
//        return atmGeoLoc;
//    }
//
//    public void setAtmGeoLoc(Map<String, Map<String, String>> atmGeoLoc) {
//        this.atmGeoLoc = atmGeoLoc;
//    }

    public HbaseDAO getHbaseDAO() {
        return hbaseDAO;
    }

    public void setHbaseDAO(HbaseDAO hbaseDAO) {
        this.hbaseDAO = hbaseDAO;
    }


}
