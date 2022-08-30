package com.zap.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
*@author: ZhuApeng
*@description: 统一返回结果
*@Date: 2022/5/10 16:31
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private boolean isSuccess;
    private HashMap<String,Object> data = new HashMap<>();

    public static Result ok(){
        Result r = new Result();
        r.setCode(ResultStatus.SUCCESS_CODE);
        r.setMsg("成功！");
        r.setSuccess(true);
        return r;
    }

    public static Result error(){
        Result r = new Result();
        r.setCode(ResultStatus.FAIL_CODE);
        r.setMsg("失败！");
        r.setSuccess(false);
        return r;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public Result isSuccess(boolean isSuccess){
        this.setSuccess(isSuccess);
        return this;
    }

    public Result map(HashMap map){
        this.setData(map);
        return this;
    }

    public Result data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}

