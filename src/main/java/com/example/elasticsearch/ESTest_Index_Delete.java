package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 2021/5/29 11:51
 */
public class ESTest_Index_Delete {
    public static void main(String[] args) throws IOException {

        // 建立Es客户端连接
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        // 删除索引
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);

        // 返回删除
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);

        // 关闭Es连接
        esClient.close();
    }
}
