package com.chs.service;

import java.util.List;
import com.chs.exception.InvalidEntityDetailsException;

import com.chs.dto.OrderDto;

public interface OrderService {

	OrderDto saveOrderDto(OrderDto orderDto) throws InvalidEntityDetailsException;

	OrderDto findOrderById(Long id) throws InvalidEntityDetailsException;

	OrderDto editOrderDto(OrderDto orderDto) throws InvalidEntityDetailsException;

	OrderDto deleteOrderDto(Long id) throws InvalidEntityDetailsException;

	List<OrderDto> findAllOrderDtos();

	List<OrderDto> findAllOrdersOfCustomer(Long id) throws InvalidEntityDetailsException;

}
