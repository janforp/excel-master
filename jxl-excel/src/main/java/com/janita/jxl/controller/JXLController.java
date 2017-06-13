package com.janita.jxl.controller;

import com.janita.excel.common.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Janita on 2017/6/13 0013- 下午 4:45
 * 该类是：
 */
@RestController
@RequestMapping("/jxl")
public class JXLController {


    @PostMapping("/down1")
    @ApiOperation(value = "jxl生成excel页面直接下载")
    public Result down1(HttpServletResponse response) {




        return Result.success();
    }
}
