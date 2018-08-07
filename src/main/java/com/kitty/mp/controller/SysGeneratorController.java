package com.kitty.mp.controller;

import com.kitty.mp.service.SysGeneratorService;
import com.kitty.mp.utils.PageUtils;
import com.kitty.mp.utils.Query;
import com.kitty.mp.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author xxx
 * @email xxx@xx.com
 * @date 2016年12月19日 下午9:12:58
 */
@RestController
@RequestMapping("/sys/generator")
@Api(description = "字典表")
public class SysGeneratorController {
    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(value = "字典查询")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.queryList(query);
        int total = sysGeneratorService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 生成代码
     */
    @RequestMapping("/code")
    @ApiOperation(value = "代码生成")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {

//		tableNames = JSON.parseArray(tables).toArray(tableNames);
        String[] tables = {"T_USER"};
        byte[] data = sysGeneratorService.generatorCode(tables);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"idcicp.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
