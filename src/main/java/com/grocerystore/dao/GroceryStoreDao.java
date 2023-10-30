package com.grocerystore.dao;

import com.grocerystore.entity.GroceryStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryStoreDao extends JpaRepository<GroceryStore, Long> {
}
