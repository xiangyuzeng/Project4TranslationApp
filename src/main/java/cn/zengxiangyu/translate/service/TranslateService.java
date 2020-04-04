package cn.zengxiangyu.translate.service;


import com.alibaba.fastjson.JSONArray;

public interface TranslateService {

    public String translateFromInput(String inputValue);

    JSONArray getAllRecords();
}
