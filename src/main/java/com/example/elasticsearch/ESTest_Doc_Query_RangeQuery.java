package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Doc_Query_RangeQuery {
    public static void main(String[] args) throws IOException {

        // 建立Es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 范围查询
        SearchRequest request = new SearchRequest().indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 组装查询条件
        RangeQueryBuilder ageQuery
                = QueryBuilders.rangeQuery("age").gte(30).lte(40);
        builder.query(ageQuery);

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        // 遍历查询结果
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit result : hits) {
            System.out.println(result.getSourceAsString());
        }

        // 关闭Es客户端连接
        esClient.close();
    }
}
