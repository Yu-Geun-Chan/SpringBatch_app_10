package com.koreait.exam.springbatch_app_10.app.calculate.controller;

import com.koreait.exam.springbatch_app_10.app.calculate.service.CalculateService;
import com.koreait.exam.springbatch_app_10.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/adm/calculate")
@RequiredArgsConstructor
public class AdmCalculateController {
    private final CalculateService calculateService;

    @GetMapping("/makeData")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showMakeData() {
        return "adm/calculate/makeData";
    }

    @PostMapping("/makeData")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public String makeData(String yearMonth) {
        calculateService.makeDate(yearMonth);
        return "success";
    }
}
