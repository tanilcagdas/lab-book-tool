package com.labbooktool.repository;

import java.util.List;

import com.labbooktool.model.Item;

public interface ItemRepository {
	
	List<Item> findAll();

	void release(int id);

	void reserve(int id, String username);

	Item findById(int id);

}
