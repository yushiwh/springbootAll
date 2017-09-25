package com.ys.controller;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yushi on 2017/2/5.
 */
@RestController
public class TestController {



    @RequestMapping("/test")
    public String index() {
        return "ok ";
    }



    @Value("${spring.solr.host}")
    private String solrStr ;
    public String getSolrStr() {
        return solrStr;
    }

    public void setSolrStr(String solrStr) {
        this.solrStr = solrStr;
    }





    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void test() throws Exception {

        //实例化solr的对象
        SolrServer solrServer = new HttpSolrServer(getSolrStr());
        //实例化添加数据类
        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.setField("id","1001");
        doc1.setField("name","iphone6s手机");
        doc1.setField("price","6000");
        doc1.setField("url","/image/001.jpg");

        //实例化添加数据类
        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.setField("id","1002");
        doc2.setField("name","三星S6手机");
        doc2.setField("price","5000");
        doc2.setField("url","/image/002.jpg");

        //设置服务器保存信息并提交
        solrServer.add(doc1);

        //设置服务器并保存信息提交
        solrServer.add(doc2);

        solrServer.commit();

    }




    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public void search() throws Exception {

        //实例化solr的对象
        SolrServer solrServer = new HttpSolrServer(getSolrStr());
        //查询类
        SolrQuery solrQuery = new SolrQuery();
        //查询关键词
        solrQuery.set("q","name:手机");
        //查询数据
        QueryResponse response = solrServer.query(solrQuery);

        //取数据
        SolrDocumentList solrList = response.getResults();
        long num = solrList.getNumFound();//得到查询的总条数
        System.out.println("条数："+num);
        for(SolrDocument sd :solrList){
            String id = (String)sd.get("id");
            String name = (String)sd.get("name");
            String url = (String)sd.get("url");
            Float price = (Float)sd.get("price");
            System.out.println("id:"+id);
            System.out.println("name:"+name);
            System.out.println("url:"+url);
            System.out.println("price:"+price);

        }

    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public void del() throws Exception {

        //实例化solr的对象
        SolrServer solrServer = new HttpSolrServer(getSolrStr());
        solrServer.deleteById("1");
        solrServer.commit();

    }
}














