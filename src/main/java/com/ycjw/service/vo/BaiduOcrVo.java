package com.ycjw.service.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Data
public class BaiduOcrVo {
    @JSONField(name = "words_result_num")
    private int wordResultNum;
    @JSONField(name = "words_result")
    private List<WordVo> wordsResult;

    public static BaiduOcrVo of(){
        BaiduOcrVo baiduOcrVo = new BaiduOcrVo();
        baiduOcrVo.setWordResultNum(0);
        baiduOcrVo.setWordsResult(Collections.singletonList(new WordVo("")));
        return baiduOcrVo;
    }

    public static BaiduOcrVo of(String keyword){
        BaiduOcrVo baiduOcrVo = new BaiduOcrVo();
        baiduOcrVo.setWordResultNum(1);
        baiduOcrVo.setWordsResult(Collections.singletonList(new WordVo(keyword)));
        return baiduOcrVo;
    }

    public boolean isLegal(){
        return wordResultNum != 0 && !CollectionUtils.isEmpty(wordsResult);
    }

}
