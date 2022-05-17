package com.kuaidi100.sdk.api;

import com.google.gson.Gson;
import com.kuaidi100.sdk.contant.ApiInfoConstant;
import com.kuaidi100.sdk.core.BaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.BaseRequest;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.response.QueryTrackResp;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 实时查询
 *
 * @Author: api.kuaidi100.com
 * @Date: 2022-05-14 16:27
 */
public  class QueryTrack extends BaseClient {

    public String getApiUrl(BaseRequest request) {
        return ApiInfoConstant.QUERY_URL;
    }

    public QueryTrackResp queryTrack(QueryTrackReq queryTrackReq) throws Exception{
        Map<String, String> header = new HashMap<>();
        String secret = "cca2b3d45e5a40d5af9b278s75ff1d8a";
        header.put("API-Key", "BqPKpMOlExTa4367");
        String param = new Gson().toJson(queryTrackReq);
        String plainText = param + "BqPKpMOlExTa4367" + secret;
        String signature = DigestUtils.md5Hex(plainText).toUpperCase();
        header.put("signature", signature);
        HttpResult httpResult = execute(queryTrackReq, header);
        if (httpResult.getStatus() == HttpStatus.SC_OK && StringUtils.isNotBlank(httpResult.getBody())){
          return new Gson().fromJson(httpResult.getBody(),QueryTrackResp.class);
        }
        return null;
    }

    @Override
    public HttpResult execute(BaseRequest request) throws Exception {
        return null;
    }
}
