package com.example.dao;

import com.example.model.AddressTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressTable, Integer> {
}
