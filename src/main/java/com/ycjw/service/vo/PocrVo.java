package com.ycjw.service.vo;

import lombok.Data;

import java.util.List;

@Data
public class PocrVo {
    private String msg;
    private List<List<PocrWordVo>> results;
}
