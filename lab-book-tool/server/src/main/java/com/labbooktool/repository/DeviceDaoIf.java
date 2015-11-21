package com.labbooktool.repository;

import java.util.List;

import com.labbooktool.model.Item;

public interface DeviceDaoIf extends ItemRepository{

	List<Item> findAll();

}
