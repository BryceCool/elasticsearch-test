package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Doc_Query_BoolQuery {
    public static void main(String[] args) throws IOException {

        // 建立Es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 组合查询数据
        SearchRequest request = new SearchRequest().indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 组合条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("age", 30));
        boolQuery.must(QueryBuilders.matchQuery("sex", "男"));

        builder.query(boolQuery);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        // 遍历查询结果
        SearchHits searchResult = response.getHits();
        System.out.println(searchResult.getTotalHits());

        for (SearchHit item : searchResult) {
            System.out.println(item.getSourceAsString());
        }

        // 关闭Es客户端连接
        esClient.close();
    }
}
