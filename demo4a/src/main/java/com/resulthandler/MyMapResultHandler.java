package com.resulthandler;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lingban on 2017/6/19.
 */
public class MyMapResultHandler implements ResultHandler {

    private final Map mappedResults = new HashMap();

    @Override
    public void handleResult(ResultContext context) {
        Map map = (Map)context.getResultObject();
        mappedResults.put(map.get("key"),map.get("value"));
    }

    public Map getMapResults(){
        return mappedResults;
    }
}
