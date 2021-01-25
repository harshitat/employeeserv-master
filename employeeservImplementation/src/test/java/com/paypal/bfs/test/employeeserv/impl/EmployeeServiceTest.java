package com.paypal.bfs.test.employeeserv.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeservApplication.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeResource service;
	
	@Before
	public void before(){
		Employee employee = new Employee();
		employee.setFirstName("first");
		employee.setLastName("last");
		employee.setDateOfBirth("2020-01-25");
		
		Address address = new Address();
		address.setLine1("line1");
		address.setLine2("line2");
		address.setCity("city");
		address.setCountry("country");
		address.setZipCode("123456");
		
		employee.setAddress(address);
		
		service.createEmployee(employee);
	}
	@Test
	public void getAllContacts(){
		ResponseEntity<Employee> employee= service.employeeGetById("1");
		assertTrue("first".equals(employee.getBody().getFirstName()));
		assertTrue("last".equals(employee.getBody().getLastName()));
		assertTrue("city".equals(employee.getBody().getAddress().getCity()));
		assertTrue("123456".equals(employee.getBody().getAddress().getZipCode()));
	}
	
}
