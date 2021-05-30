package com.example.elasticsearch;

import com.example.elasticsearch.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Doc_Insert {
    public static void main(String[] args) throws IOException {

        // 建立客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 插入数据
        IndexRequest request = new IndexRequest();
        // 组装数据
        request.index("user").id("1001");
        User user = new User();
        user.setName("张三");
        user.setSex("男");
        user.setAge(18);

        // 向Es中插入数据，必须将数据转换为Json格式
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        request.source(userStr, XContentType.JSON);

        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

        // 返回值
        System.out.println(response.getResult());

        // 关闭客户端
        esClient.close();
    }
}
