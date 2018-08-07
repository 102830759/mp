package com.kitty.mp.utils;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kemi on 2016/3/12.
 */
@ApiModel
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1244437270503922868L;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录条数")
    private int total;
    /**
     * 每页数据
     */
    @ApiModelProperty(value = "每页数据列表")
    private List<T> rows;
    @ApiModelProperty(value = "查询条件")
    private Map<String, Object> parameter;

    /**
     * 每页数据条数
     */
    private int limit;
    /**
     * 当前页面
     */
    private int current;
    /**
     * 起始行号
     */
    private int begin;
    /**
     * 结束行号
     */
    private int end;

    public Page() {
    }


    public boolean getIndexCode(String[] arr, String code) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(code))
                return true;
        }
        return false;
    }

    public Page(int current, int limit) {
        parameter = new HashMap<String, Object>();
        if (current < 1) {
            current = 1;
        }
        if (limit < 1) {
            limit = 10;
        }
        this.limit = limit;
        this.current = current;
        this.begin = (current - 1) * limit;
        this.end = current * limit;
        parameter.put("begin", begin);
        parameter.put("end", end);
        parameter.put("limit", limit);
        parameter.put("current", current);
    }

    public Page(Map<String, Object> parameter){
        this.parameter = parameter;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void addParameter(String key, Object value) {
        parameter.put(key, value);
    }

    public void addParameters(Map<String, Object> parameters) {
        parameter.putAll(parameters);
    }

    public Map<String, Object> getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "Page{" +
                ", total=" + total +
                ", rows=" + rows +
                ", parameter=" + parameter +
                ", limit=" + limit +
                ", current=" + current +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
