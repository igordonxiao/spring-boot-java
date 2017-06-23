package io.github.igordonxiao.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Health Check Controller
 */
@Api(value = "健康检查", description = "健康检查")
@Controller
@RequestMapping("/healthCheck")
public class HealthCheckController {

    /**
     * Health Check, Response HttpStatus Code for 200
     *
     * @return HttpStatus
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ResponseBody
    public HttpStatus healthCheck() {
        return HttpStatus.OK;
    }
}

