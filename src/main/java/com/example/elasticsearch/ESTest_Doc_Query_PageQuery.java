package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import javax.management.Query;
import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Doc_Query_PageQuery {
    public static void main(String[] args) throws IOException {

        // 建立Es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 分页查询数据
        SearchRequest request = new SearchRequest().indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // 分页查询
        builder.from(0);
        builder.size(2);
        // 排序查询
        builder.sort("age", SortOrder.DESC);
        // 过滤字段查询
        String[] includeFields = {"name", "age"};
        String[] excludeFields = {};
        builder.fetchSource(includeFields, excludeFields);
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
