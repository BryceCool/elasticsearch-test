package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Index_Search {
    public static void main(String[] args) throws IOException {

        // 建立客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 查询索引
        GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse response = esClient.indices().get(request, RequestOptions.DEFAULT);

        // 查询结果
        System.out.println(response.getAliases());
        System.out.println(response.getSettings());
        System.out.println(response.getMappings());

        // 关闭客户端
        esClient.close();
    }
}
