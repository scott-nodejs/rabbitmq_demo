package com.xl.demo;

import com.xl.demo.common.OrderEnum;
import com.xl.demo.entity.Order;
import com.xl.demo.service.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping(value = "{id}")
    public Object order(@PathVariable int id){
        Order order = new Order();
        order.setUserId(1);
        order.setGoodId(id);
        order.setState(OrderEnum.WAIT.getState());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        orderService.order(order);
        Map<String,String> map = new HashMap<>();
        map.put("1","下单成功");
        return map;
    }
}
