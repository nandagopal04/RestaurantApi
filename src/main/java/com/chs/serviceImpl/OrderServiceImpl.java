package com.chs.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chs.dto.OrderDto;
import com.chs.entity.Customer;
import com.chs.entity.Item;
import com.chs.entity.Order;
import com.chs.exception.InvalidEntityDetailsException;
import com.chs.repository.CustomerRepo;
import com.chs.repository.OrderRepo;
import com.chs.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepo customerRepo;

	@Transactional
	@Override
	public OrderDto saveOrderDto(OrderDto orderDto) throws InvalidEntityDetailsException {
		System.out.println("@@@@ "+orderDto.getCustomer().getId());
		Long custId=orderDto.getCustomer().getId();
		Optional<Customer> optCustomer = customerRepo.findById(custId);
		System.out.println("@@@@ cust"+optCustomer);

		if(!optCustomer.isPresent()) {
			throw new InvalidEntityDetailsException("Invalid Customer ID: "+orderDto.getCustomer().getId());
		}
		Order order = convertOrderDtoToOrder(orderDto);
		System.out.println("@@@@ fff"+order.toString());

		order.setPrice(priceCalc(orderDto.getListOfItems()));
		System.out.println("@@@@ ttt"+order);

		order = orderRepo.save(order);
		System.out.println("@@@@ ggg"+order);

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
		findOrderById(orderDto.getId());
		Order order = convertOrderDtoToOrder(orderDto);
		order.setPrice(priceCalc(order.getListOfItems()));
		order = orderRepo.save(order);
		return convertOrderToOrderDto(order);
	}

	@Override
	public OrderDto deleteOrderDto(Long id) throws InvalidEntityDetailsException {
		OrderDto orderDto = findOrderById(id);
		Order order = convertOrderDtoToOrder(orderDto);
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

	private Order convertOrderDtoToOrder(OrderDto orderDto) {
		return modelMapper.map(orderDto, Order.class);
	}
	
	private Double priceCalc(List<Item> items) {
		return items.stream().mapToDouble(Item::getPrice).sum();
	}

}
