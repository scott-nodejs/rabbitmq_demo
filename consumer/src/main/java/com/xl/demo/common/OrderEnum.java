package com.xl.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderEnum {
    WAIT(0), // 等待支付
    FINISH(1), // 完成支付
    CENCEL(2); // 取消订单

    private int state;
}
