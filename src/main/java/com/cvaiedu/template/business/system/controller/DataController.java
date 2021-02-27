package com.cvaiedu.template.business.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.cvaiedu.template.result.Result;
import com.cvaiedu.template.util.WakatimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/data")
public class DataController {

    private Logger logger = LoggerFactory.getLogger(DataController.class);

    @GetMapping("/allTimeSinceToday")
    public Result getAllTimeSinceToday(@RequestParam Map<String, Object> parameters) {
        JSONObject jsonObject = WakatimeUtils.allTimeSinceToday(parameters);
        return Result.ok(jsonObject);
    }

    @GetMapping("/dataDumps")
    public Result getDataDumps() {
        JSONObject jsonObject = WakatimeUtils.dataDumps();
        return Result.ok(jsonObject);
    }

    @GetMapping("/durations")
    public Result getDurations(@RequestParam Map<String, Object> parameters) {
        JSONObject jsonObject = WakatimeUtils.durations(parameters);
        return Result.ok(jsonObject);
    }

    @GetMapping("/editors")
    public Result getEditors(@RequestParam Map<String, Object> parameters) {
        JSONObject jsonObject = WakatimeUtils.editors(parameters);
        return Result.ok(jsonObject);
    }

    @GetMapping("/goals")
    public Result getGoals() {
        JSONObject jsonObject = WakatimeUtils.goals();
        return Result.ok(jsonObject);
    }

    @GetMapping("/stats")
    public Result getStats(@RequestParam Map<String, Object> parameters) {
        JSONObject jsonObject = WakatimeUtils.stats(parameters);
        return Result.ok(jsonObject);
    }
}
