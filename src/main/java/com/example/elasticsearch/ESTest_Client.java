package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Client {
    public static void main(String[] args) throws IOException {

        // 创建Es 客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"))
        );

        System.out.println("Es Client Has Connect!");
        // 关闭连接
        esClient.close();
    }
}
