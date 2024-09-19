package com.chs.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.chs.dto.OrderDto;
import com.chs.entity.Customer;
import com.chs.entity.Order;
import com.chs.exception.InvalidEntityDetailsException;
import com.chs.repository.CustomerRepo;
import com.chs.repository.OrderRepo;
import com.chs.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public OrderDto saveOrderDto(OrderDto orderDto) {
		Order order = convertOrderDtoToOrderDto(orderDto);
		order = orderRepo.save(order);
		return convertOrderToOrderDto(order);
	}

	@Override
	public OrderDto findOrderById(Long id) throws InvalidEntityDetailsException {
		Optional<Order> optOrder = orderRepo.findById(id);
		if (!optOrder.isPresent()) {
			throw new InvalidEntityDetailsException("Invalid order ID: " + id);
		}
		return convertOrderToOrderDto(optOrder.get());
	}

	@Override
	public OrderDto editOrderDto(OrderDto orderDto) throws InvalidEntityDetailsException {
		orderDto = findOrderById(orderDto.getId());
		Order order = orderRepo.save(convertOrderDtoToOrderDto(orderDto));
		return convertOrderToOrderDto(order);
	}

	@Override
	public OrderDto deleteOrderDto(Long id) throws InvalidEntityDetailsException {
		OrderDto orderDto = findOrderById(id);
		Order order = convertOrderDtoToOrderDto(orderDto);
		orderRepo.delete(order);
		return orderDto;
	}

	@Override
	public List<OrderDto> findAllOrderDtos() {
		List<Order> allOrders = orderRepo.findAll();
		return allOrders.stream().map(this::convertOrderToOrderDto).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> findAllOrdersOfCustomer(Long id) throws InvalidEntityDetailsException {
		Optional<Customer> optCustomer = customerRepo.findById(id);
		if (!optCustomer.isPresent()) {
			throw new InvalidEntityDetailsException("Invalid Customer ID: " + id);
		}
		List<Order> allOrdersOfCustomer = orderRepo.findAllByCustomer(optCustomer.get());
		return allOrdersOfCustomer.stream().map(this::convertOrderToOrderDto).collect(Collectors.toList());
	}

	private OrderDto convertOrderToOrderDto(Order order) {
		return modelMapper.map(order, OrderDto.class);
	}

	private Order convertOrderDtoToOrderDto(OrderDto orderDto) {
		return modelMapper.map(orderDto, Order.class);
	}

}
