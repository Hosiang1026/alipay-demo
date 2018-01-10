package com.zjs.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * @author xulf
 * @createTime 2016年6月6日
 * @description 监控系统
 */
@RestController
@RequestMapping(value = "/monitor/api/v1/monitor")
@Api(value = "/monitor",description = "监控系统")
public class MonitorApi {
	
}
