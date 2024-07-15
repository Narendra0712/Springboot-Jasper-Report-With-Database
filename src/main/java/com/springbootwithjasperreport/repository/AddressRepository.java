package com.springbootwithjasperreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootwithjasperreport.model.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
