package com.evan.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
