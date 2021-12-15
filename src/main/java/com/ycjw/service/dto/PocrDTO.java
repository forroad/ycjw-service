package com.ycjw.service.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class PocrDTO {
    private List<String> images;

    public static PocrDTO of(String image){
        PocrDTO pocrDTO = new PocrDTO();
        pocrDTO.setImages(Collections.singletonList(image));
        return pocrDTO;
    }
}
