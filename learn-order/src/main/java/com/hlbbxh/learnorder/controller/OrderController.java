package com.hlbbxh.learnorder.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.hlbbxh.learnorder.Exception.OrderException;
import com.hlbbxh.learnorder.DTO.OrderDTO;
import com.hlbbxh.learnorder.VO.ResultVo;
import com.hlbbxh.learnorder.form.OrderForm;
import com.hlbbxh.learnorder.service.OrderService;
import com.hlbbxh.learnorder.converter.OrderFormToOrderDTO;
import com.hlbbxh.learnorder.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0221:54:07
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    /**
     * 1. 参数检验
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
    @PostMapping("/createOrder")
    public ResultVo<Map<String, String>> createOrder(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.error("【创建订单】参数不正确,orderForm={}",orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        //orderform 转换成orderdto
        OrderFormToOrderDTO orderFormToOrderDTO = new OrderFormToOrderDTO();
        OrderDTO convertOrderDTO = orderFormToOrderDTO.convert(orderForm);
        //判断购物车 是不是空的
        if(CollectionUtil.isEmpty(convertOrderDTO.getOrderDetails())){
            logger.error("【创建订单】购物车为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        //写入订单数据
        OrderDTO resOrder = orderService.createOrder(convertOrderDTO);
        HashMap<String, String> dataMap = new HashMap<>(16);
        dataMap.put("orderId",resOrder.getOrderId());
        return new ResultVo<>(dataMap);
    }

}
