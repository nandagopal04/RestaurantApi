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
import org.springframework.web.bind.annotation.RequestBody;

import com.chs.dto.ItemDto;
import com.chs.exception.InvalidEntityDetailsException;
import com.chs.service.ItemService;


@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@PostMapping("/create")
	public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto){
		ItemDto savedItemDto = itemService.saveItem(itemDto);
		return new ResponseEntity<ItemDto>(savedItemDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ItemDto> findItemById(@PathVariable Long id) throws InvalidEntityDetailsException{
		ItemDto itemDto = itemService.findItemById(id);
		return new ResponseEntity<ItemDto>(itemDto, HttpStatus.OK);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<ItemDto>> findAllItems(){
		List<ItemDto> allItemDtos = itemService.findAllItems();
		return new ResponseEntity<List<ItemDto>>(allItemDtos, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto) throws InvalidEntityDetailsException{
		ItemDto editedItemDto = itemService.editItem(itemDto);
		return new ResponseEntity<ItemDto>(editedItemDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ItemDto> deleteItem(@PathVariable Long id) throws InvalidEntityDetailsException{
		ItemDto itemDto = itemService.deleteItem(id);
		return new ResponseEntity<ItemDto>(itemDto, HttpStatus.OK);
	}
	
}
