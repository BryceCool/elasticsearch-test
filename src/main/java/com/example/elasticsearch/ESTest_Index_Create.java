package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Index_Create {
    public static void main(String[] args) throws IOException {

        // 建立客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 创建索引
        CreateIndexRequest indexRequest = new CreateIndexRequest("user");
        CreateIndexResponse response = esClient.indices()
                .create(indexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println("创建索引结果：" + acknowledged);
        // 关闭客户端
        esClient.close();
    }
}
