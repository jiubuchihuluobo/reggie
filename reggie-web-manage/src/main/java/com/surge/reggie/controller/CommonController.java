package com.surge.reggie.controller;

import com.surge.common.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {

    @PostMapping("/upload")
    public Response<Object> upload() {
        return Response.success("https://tanhua-gxm.oss-cn-beijing.aliyuncs.com/reggie/e476f679-5c15-436b-87fa-8c4e9644bf33.jpeg");
    }

}
