package com.grocerystore.dao;

import com.grocerystore.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao  extends JpaRepository<Address, Long> {
}
