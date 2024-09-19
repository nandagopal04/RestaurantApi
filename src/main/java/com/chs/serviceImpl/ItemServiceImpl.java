package com.chs.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.chs.dto.ItemDto;
import com.chs.entity.Item;
import com.chs.exception.InvalidEntityDetailsException;
import com.chs.repository.ItemRepo;
import com.chs.service.ItemService;

public class ItemServiceImpl implements ItemService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Override
	public ItemDto saveItem(ItemDto itemDto) {
		Item item = convertItemDtoToItem(itemDto);
		item = itemRepo.save(item);
		return convertItemToItemDto(item);
	}

	@Override
	public ItemDto findItemById(Long id) throws InvalidEntityDetailsException {
		Optional<Item> optItem = itemRepo.findById(id);
		if(!optItem.isPresent()) {
			throw new InvalidEntityDetailsException("Invalid item ID: "+id);
		}
		return convertItemToItemDto(optItem.get());
	}

	@Override
	public List<ItemDto> findAllItems() {
		List<Item> allItems = itemRepo.findAll();
		return allItems
				.stream()
				.map(this::convertItemToItemDto)
				.collect(Collectors.toList());
	}

	@Override
	public ItemDto editItem(ItemDto itemDto) throws InvalidEntityDetailsException {
		itemDto = findItemById(itemDto.getId());
		Item item = itemRepo.save(convertItemDtoToItem(itemDto));
		return convertItemToItemDto(item);
	}

	@Override
	public ItemDto deleteItem(Long id) throws InvalidEntityDetailsException {
		ItemDto itemDto = findItemById(id);
		itemRepo.delete(convertItemDtoToItem(itemDto));
		return itemDto;
	}

	private Item convertItemDtoToItem(ItemDto itemDto) {
		return modelMapper.map(itemDto, Item.class);
	}
	private ItemDto convertItemToItemDto(Item item) {
		return modelMapper.map(item, ItemDto.class);
	}

}
