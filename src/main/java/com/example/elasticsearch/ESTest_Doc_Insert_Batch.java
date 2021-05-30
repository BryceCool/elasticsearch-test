package com.example.elasticsearch;

import com.example.elasticsearch.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Doc_Insert_Batch {
    public static void main(String[] args) throws IOException {

        // 建立Es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        BulkRequest request = new BulkRequest();

        request.add(new IndexRequest().index("user").id("1001")
                .source(XContentType.JSON, "name", "ZhangSan","age",30,"sex","男"));
        request.add(new IndexRequest().index("user").id("1002")
                .source(XContentType.JSON, "name", "LiSi","age",30,"sex","男"));
        request.add(new IndexRequest().index("user").id("1003")
                .source(XContentType.JSON, "name", "WangWu","age",40,"sex","男"));
        request.add(new IndexRequest().index("user").id("1004")
                .source(XContentType.JSON, "name", "WangWu1","age",40,"sex","女"));
        request.add(new IndexRequest().index("user").id("1005")
                .source(XContentType.JSON, "name", "WangWu2","age",50,"sex","男"));
        request.add(new IndexRequest().index("user").id("1006")
                .source(XContentType.JSON, "name", "WangWu3","age",50,"sex","女"));

        BulkResponse bulk = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulk.getTook());
        System.out.println(bulk.getItems());


        // 关闭Es客户端连接
        esClient.close();
    }
}
