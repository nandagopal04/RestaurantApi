package com.chs.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chs.dto.CustomerDto;
import com.chs.entity.Customer;
import com.chs.exception.InvalidEntityDetailsException;
import com.chs.repository.CustomerRepo;
import com.chs.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Override
	public CustomerDto saveCustomer(CustomerDto customerDto) {
		Customer customer = convertCustomerDtoToCustomer(customerDto);
		customer = customerRepo.save(customer);
		return convertCustomerToCustomerDto(customer);
	}

	@Override
	public CustomerDto findCustomerById(Long id) throws InvalidEntityDetailsException {
		Optional<Customer> optCustomer = customerRepo.findById(id);
		if(!optCustomer.isPresent()) {
			throw new InvalidEntityDetailsException("No customer exits with ID: "+id);
		}
		return convertCustomerToCustomerDto(optCustomer.get());
	}

	@Override
	public List<CustomerDto> findAllCustomers() {
		List<Customer> allCustomers = customerRepo.findAll();
		if(allCustomers.isEmpty()) {
			return null;
		}
		return allCustomers
				.stream()
				.map(this::convertCustomerToCustomerDto)
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDto editCustomer(CustomerDto customerDto) throws InvalidEntityDetailsException {
//		customerDto = findCustomerById(customerDto.getId());
		if(!customerRepo.existsById(customerDto.getId())) {
			throw new InvalidEntityDetailsException("Invalid customer ID: "+customerDto.getId());
		}
		Customer customer = customerRepo.save(convertCustomerDtoToCustomer(customerDto));
		return convertCustomerToCustomerDto(customer);
	}

	@Override
	public CustomerDto deleteCustomer(Long id) throws InvalidEntityDetailsException {
		CustomerDto customerDto = findCustomerById(id);
		customerRepo.delete(convertCustomerDtoToCustomer(customerDto));
		return customerDto;
	}
	
	@Override
	public CustomerDto findCustomerByPhoneNumber(Long phoneNumber) throws InvalidEntityDetailsException {
		List<Customer> allCustomers = new ArrayList<Customer>();
		allCustomers = customerRepo.findAll();
		for(Customer customer : allCustomers) {
			if(customer.getPhoneNumber().equals(phoneNumber)) {
				return convertCustomerToCustomerDto(customer);
			}
		}
		throw new InvalidEntityDetailsException("Phone Number provided: " + phoneNumber + " doesn't exist");
	}
	
	private CustomerDto convertCustomerToCustomerDto(Customer customer) {
		return modelMapper.map(customer, CustomerDto.class);
	}
	private Customer convertCustomerDtoToCustomer(CustomerDto customerDto) {
		return modelMapper.map(customerDto, Customer.class);
	}

}
