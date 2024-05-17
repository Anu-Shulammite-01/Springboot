package com.example.EntityMapping.Repository;

import com.example.EntityMapping.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
