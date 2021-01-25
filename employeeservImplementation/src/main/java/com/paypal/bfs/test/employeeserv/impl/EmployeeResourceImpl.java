package com.paypal.bfs.test.employeeserv.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.model.AddressDetails;
import com.paypal.bfs.test.employeeserv.model.Employees;
import com.paypal.bfs.test.employeeserv.repository.EmployeeResourceRepository;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	@Autowired
	private EmployeeResourceRepository repository;
	
    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

    	Optional<Employees> employees = repository.findById(Long.valueOf(id));
    	Employee e = new Employee();
    	if(employees.isPresent()) {
    		e = getEmployee(employees.get());
    	}
        return new ResponseEntity<>(e, HttpStatus.OK);
    }
    private Employee getEmployee(Employees employees) {
    	Employee e = new Employee();
    	BeanUtils.copyProperties(employees, e);
    	if(employees.getAddress()!=null) {
    		Address address = new Address();
    		address.setCity(employees.getAddress().getCity());
    		address.setCountry(employees.getAddress().getCountry());
    		address.setLine1(employees.getAddress().getLine1());
    		address.setLine2(employees.getAddress().getLine2());
    		address.setState(employees.getAddress().getState());
    		address.setZipCode(employees.getAddress().getZipCode());
    		
    		e.setAddress(address);
    	}
    	return e;
    }
	@Override
	public ResponseEntity<String> createEmployee(Employee employee) {
		Employees employees = getEmployees(employee);
		AddressDetails address = employees.getAddress();
		address.setEmployees(employees);
		employees.setAddress(address);
		repository.save(employees);
		return new ResponseEntity<>("Created", HttpStatus.CREATED);
	}
	private Employees getEmployees(Employee employee) {
    	Employees e = new Employees();
    	BeanUtils.copyProperties(employee, e);
    	if(employee.getAddress()!=null) {
    		AddressDetails address = new AddressDetails();
    		address.setCity(employee.getAddress().getCity());
    		address.setCountry(employee.getAddress().getCountry());
    		address.setLine1(employee.getAddress().getLine1());
    		address.setLine2(employee.getAddress().getLine2());
    		address.setState(employee.getAddress().getState());
    		address.setZipCode(employee.getAddress().getZipCode());

    		e.setAddress(address);
    	}
    	return e;
    }
}
