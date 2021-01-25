package com.paypal.bfs.test.employeeserv.repository;

import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.employeeserv.model.Employees;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface EmployeeResourceRepository extends CrudRepository<Employees, Long>{
	
}
