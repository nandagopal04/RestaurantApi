package com.chs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chs.dto.CustomerDto;
import com.chs.exception.InvalidEntityDetailsException;
import com.chs.serviceImpl.CustomerServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<CustomerDto> createCustomerDto(@RequestBody CustomerDto customerDto){
		CustomerDto savedCustoemrDto = customerServiceImpl.saveCustomer(customerDto);
		return new ResponseEntity<CustomerDto>(savedCustoemrDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<CustomerDto> findCustomerById(@PathVariable Long id) throws InvalidEntityDetailsException{
		CustomerDto customerDto = customerServiceImpl.findCustomerById(id);
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<CustomerDto>> findAllCustomers(){
		List<CustomerDto> allCustomerDtos = customerServiceImpl.findAllCustomers();
		return new ResponseEntity<List<CustomerDto>>(allCustomerDtos, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CustomerDto> editCustomer(@RequestBody CustomerDto customerDto) throws InvalidEntityDetailsException{
		CustomerDto editedCustomerDto = customerServiceImpl.editCustomer(customerDto);
		return new ResponseEntity<CustomerDto>(editedCustomerDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomerDto> deleteCustomerDto(@PathVariable Long id) throws InvalidEntityDetailsException{
		CustomerDto customerDto = customerServiceImpl.deleteCustomer(id);
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
	}
	
}
