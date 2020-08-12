package com.xl.demo.mapper;

import com.xl.demo.common.BaseMapper;
import com.xl.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
