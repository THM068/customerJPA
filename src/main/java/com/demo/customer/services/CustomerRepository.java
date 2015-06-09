package com.demo.customer.services;

import com.demo.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tm1c14 on 04/06/2015.
 */
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    List<Customer> findByLastName(String lastName);
}
