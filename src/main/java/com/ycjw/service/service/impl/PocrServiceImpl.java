package com.ycjw.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.ycjw.service.dto.PocrDTO;
import com.ycjw.service.service.PocrService;
import com.ycjw.service.util.HttpUtil;
import com.ycjw.service.vo.PocrVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PocrServiceImpl implements PocrService {

    @Value("${pocr.api}")
    private String pocrAPi;

    @Override
    public PocrVo pocr(String image) {
        try {
            String result = HttpUtil.bodyPost(pocrAPi, PocrDTO.of(image));
            return JSON.parseObject(result, PocrVo.class);
        }catch (Exception e){
            log.error("pocr error! exception:{}", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }
}
