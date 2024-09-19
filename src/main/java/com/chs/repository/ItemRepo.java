package com.chs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chs.entity.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long>{

}
