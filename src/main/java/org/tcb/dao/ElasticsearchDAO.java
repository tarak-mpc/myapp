package org.tcb.dao;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ElasticsearchDAO {

    /**
     * es type
     */
    private String ES_TYPE = "user";

    protected TransportClient client ;

    public ElasticsearchDAO() {
//        if (client == null) {
//            synchronized (ElasticsearchDAO.class) {
//                if (client != null) {
//                    return;
//                }
                Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
                try {
                    client = new PreBuiltTransportClient(settings)
                            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("elasticsearch"), 9300));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
//            }
//        }
    }

    /**
     * @return _id
     */
    public String createIndex(String ES_INDEX, String json) {


        IndexResponse response = client.prepareIndex(ES_INDEX, ES_TYPE).setSource(json).get();

        System.out.println(String.format("create index response: %s", response.toString()));

        return response.getId();
    }

    /**
     * @param _id
     * @return GetResponse
     */
    public GetResponse getDataResponse(String ES_INDEX, String _id) {
        GetResponse response = client.prepareGet(ES_INDEX, ES_TYPE, _id).get();
        System.out.println(String.format("get data response: %s", JSON.toJSONString(response.getSource())));
        return response;
    }

    /**
     * @param queryBuilder
     * @return List
     */
    public List<String> queryDataList(String ES_INDEX, QueryBuilder queryBuilder) {
        SearchResponse sResponse = client.prepareSearch(ES_INDEX).setTypes(ES_TYPE).setQuery(queryBuilder).setSize(1000).execute().actionGet();
        SearchHits hits = sResponse.getHits();

        List<String> list = new ArrayList<>();
        SearchHit[] hitArray = hits.hits();
        for (SearchHit hit : hitArray) {
            Map<String, Object> map = hit.getSource();

            String username = (String) map.get("username");
            String postDate = (String) map.get("postDate");
            String message = (String) map.get("message");

            StringBuilder br = new StringBuilder();
            br.append(username).append("_").append(message).append("_").append(postDate);
            list.add(br.toString());
        }

        System.out.println(String.format("query data count=%s, list : %s", list.size(), JSON.toJSONString(list)));

        return list;
    }

    /**
     * @param index
     * @param queryBuilder
     * @return list
     */
    public List<String> queryByScroll(String index, QueryBuilder queryBuilder) {

        // 100 hits per shard will be returned for each scroll
        SearchResponse scrollResp = client.prepareSearch(index).addSort(FieldSortBuilder.DOC_FIELD_NAME,
                SortOrder.ASC).setScroll(new TimeValue(60000)).setQuery(queryBuilder).setSize(100).execute().actionGet();
        List<String> list = new ArrayList<>();
        // Scroll until no hits are returned
        while (true) {

            for (SearchHit hit : scrollResp.getHits().getHits()) {
                // Handle the hit...
                Map<String, Object> map = hit.getSource();
                list.add(JSON.toJSONString(map));
                System.out.println(String.format("scroll query data list : %s", JSON.toJSONString(map)));
            }
            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
            // Break condition: No hits are returned
            if (scrollResp.getHits().getHits().length == 0) {
                break;
            }
        }
        return list;
    }

    /**
     * @param _id
     * @return DeleteResponse
     */
    public DeleteResponse deleteDataResponse(String ES_INDEX, String _id) {
        DeleteResponse response = client.prepareDelete(ES_INDEX, ES_TYPE, _id).get();
        System.out.println(String.format("delete data response: %s", JSON.toJSONString(response)));
        return response;
    }

    /**
     * @param _id
     * @throws Exception
     */
    public void updateData(String ES_INDEX, String _id) throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(ES_INDEX);
        updateRequest.type(ES_TYPE);
        updateRequest.id(_id);

        String json = "{" + "\"username\":\"lisi\"," + "\"postDate\":\"" + DateFormat.getInstance().format(new Date()) + "\","
                + "\"message\":\"update\"" + "}";

        updateRequest.doc(json);

        client.update(updateRequest).get();
    }

    public void shutdown() {
        if (client != null) {
            client.close();
        }
    }

    public static void main(String[] args) throws Exception {


//        ElasticsearchDAO elasticsearchDAO1 = ElasticsearchInstance.INSTANCE.getElasticsearchDAO();
//
//        elasticsearchDAO1.createIndex("hello","{" + "\"username\":\"lisi\"," + "\"postDate\":\"" + DateFormat.getInstance().format(new Date()) + "\","
//                + "\"message\":\"update\"" + "}");
//
//        ElasticsearchDAO elasticsearchDAO2 = ElasticsearchInstance.INSTANCE.getElasticsearchDAO();
//
//        elasticsearchDAO2.createIndex("hello","{" + "\"username\":\"lisi\"," + "\"postDate\":\"" + DateFormat.getInstance().format(new Date()) + "\","
//                + "\"message\":\"update\"" + "}");
//        String _id = createIndex();
//        getDataResponse(_id);
//        updateData(_id);
//        getDataResponse(_id);
//
//        QueryBuilder builder = QueryBuilders.matchAllQuery();
//        //// QueryBuilder builder = QueryBuilders.termQuery("username", "pomelo");
//        queryByScroll(ES_INDEX, builder);
//
//        deleteDataResponse(_id);
//        shutdown();
    }



}
