package org.tcb;


import org.tcb.dao.ElasticsearchDAO;

public enum ElasticsearchInstance {
    INSTANCE;


    private ElasticsearchDAO elasticsearchDAO = new ElasticsearchDAO();


    public ElasticsearchDAO getElasticsearchDAO() {
        return elasticsearchDAO;
    }

    public void setElasticsearchDAO(ElasticsearchDAO elasticsearchDAO) {
        this.elasticsearchDAO = elasticsearchDAO;
    }


}
