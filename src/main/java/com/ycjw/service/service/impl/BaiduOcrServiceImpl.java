package com.ycjw.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.ycjw.service.service.OcrService;
import com.ycjw.service.service.PocrService;
import com.ycjw.service.util.HttpUtil;
import com.ycjw.service.vo.BaiduOcrVo;
import com.ycjw.service.vo.PocrVo;
import com.ycjw.service.vo.PocrWordVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class BaiduOcrServiceImpl implements OcrService {
    @Value("${ocr.baidu.api}")
    private String authApi;
    @Value("${ocr.baidu.auth.api}")
    private String ocrApi;
    @Value("${ocr.baidu.auth.general_api}")
    private String generalOcrApi;
    @Value("${ocr.baidu.apiKey}")
    private String apikey;
    @Value("${ocr.baidu.secretKey}")
    private String secretKey;

    @Autowired
    private PocrService pocrService;

    @Override
    public String ocr(String image) {
        try {
//            String ocrUrl = ocrApi + "?access_token=" + getAuth();
//            String result = HttpUtil.post(ocrUrl, Collections.singletonMap("image", image));
//            BaiduOcrVo baiduOcrVo = JSON.parseObject(result, BaiduOcrVo.class);
            BaiduOcrVo baiduOcrVo = pocr(image);
            if(baiduOcrVo == null || !baiduOcrVo.isLegal()){
                return JSON.toJSONString(BaiduOcrVo.of());
            }
            return JSON.toJSONString(baiduOcrVo);
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return JSON.toJSONString(BaiduOcrVo.of());
    }

    @Override
    public String generalOcr(String image) {
        try {
//            String ocrUrl = generalOcrApi + "?access_token=" + getAuth();
//            String result = HttpUtil.post(ocrUrl, Collections.singletonMap("image", image));
//            BaiduOcrVo baiduOcrVo = JSON.parseObject(result, BaiduOcrVo.class);
            BaiduOcrVo baiduOcrVo = pocr(image);
            if(baiduOcrVo == null || !baiduOcrVo.isLegal()){
                return JSON.toJSONString(BaiduOcrVo.of());
            }
            return JSON.toJSONString(baiduOcrVo);
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return JSON.toJSONString(BaiduOcrVo.of());
    }

    private String getAuth(){
        String authUrl = authApi + "?grant_type=client_credentials";
        authUrl += "&client_id=".concat(apikey);
        authUrl += "&client_secret=".concat(secretKey);

        String authResult = HttpUtil.get(authUrl);
        return JSON.parseObject(authResult).getString("access_token");
    }

    private BaiduOcrVo pocr(String image){
        PocrVo pocrVo = pocrService.pocr(image);
        if(pocrVo == null){
            return BaiduOcrVo.of();
        }
        return buildBaiduOcrVo(pocrVo);
    }

    private BaiduOcrVo buildBaiduOcrVo(PocrVo pocrVo){
        String result = Optional.ofNullable(pocrVo)
                .map(PocrVo::getResults)
                .map(r -> r.get(0))
                .map(r -> r.get(0))
                .map(PocrWordVo::getText)
                .orElse("");
        return BaiduOcrVo.of(result);
    }
}
