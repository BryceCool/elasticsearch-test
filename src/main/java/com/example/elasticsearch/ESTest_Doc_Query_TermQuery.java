package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Doc_Query_TermQuery {
    public static void main(String[] args) throws IOException {

        // 建立Es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 条件查询数据
        SearchRequest request = new SearchRequest().indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.termQuery("age", 30));
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
