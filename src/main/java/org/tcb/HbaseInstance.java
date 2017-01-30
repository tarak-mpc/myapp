package org.tcb;


import org.tcb.dao.HbaseDAO;

public enum HbaseInstance {
    INSTANCE;

    private HbaseDAO hbaseDAO = new HbaseDAO();

    public HbaseDAO getHbaseDAO() {
        return hbaseDAO;
    }

    public void setHbaseDAO(HbaseDAO hbaseDAO) {
        this.hbaseDAO = hbaseDAO;
    }


}
