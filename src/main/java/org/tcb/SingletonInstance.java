package org.tcb;


import org.tcb.dao.ElasticsearchDAO;
import org.tcb.dao.HbaseDAO;

public enum SingletonInstance {
    INSTANCE;

    private HbaseDAO hbaseDAO = new HbaseDAO();

    private ElasticsearchDAO elasticsearchDAO = new ElasticsearchDAO();

    public HbaseDAO getHbaseDAO() {
        return hbaseDAO;
    }

    public void setHbaseDAO(HbaseDAO hbaseDAO) {
        this.hbaseDAO = hbaseDAO;
    }

    public ElasticsearchDAO getElasticsearchDAO() {
        return elasticsearchDAO;
    }

    public void setElasticsearchDAO(ElasticsearchDAO elasticsearchDAO) {
        this.elasticsearchDAO = elasticsearchDAO;
    }


}
