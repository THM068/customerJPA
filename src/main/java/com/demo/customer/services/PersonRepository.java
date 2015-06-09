package com.demo.customer.services;

import com.demo.customer.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Iterator;

/**
 * Created by tm1c14 on 09/06/2015.
 */
public interface PersonRepository extends PagingAndSortingRepository <Person, Long> {


}
