package com.chs.service;

import java.util.List;

import com.chs.dto.ItemDto;
import com.chs.exception.InvalidEntityDetailsException;

public interface ItemService {

	ItemDto saveItem(ItemDto itemDto);

	ItemDto findItemById(Long id) throws InvalidEntityDetailsException;

	List<ItemDto> findAllItems();

	ItemDto editItem(ItemDto itemDto) throws InvalidEntityDetailsException;

	ItemDto deleteItem(Long id) throws InvalidEntityDetailsException;

}
