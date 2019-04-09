package com.evan.utils;


import com.evan.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO(0, "成功", object);
        return resultVO;
    }

    public static ResultVO fail(Object object) {
        ResultVO resultVO = new ResultVO(-1, "失败", object);
        return resultVO;
    }
}
