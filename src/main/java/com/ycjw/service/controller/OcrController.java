package com.ycjw.service.controller;

import com.ycjw.service.service.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ocr")
public class OcrController {

    @Autowired
    private OcrService ocrService;

    @GetMapping
    public String ocr(@RequestParam("image") String image){
        return ocrService.ocr(image);
    }

    @GetMapping("/general")
    public String generalOcr(@RequestParam("image") String image){
        return ocrService.generalOcr(image);
    }
}
