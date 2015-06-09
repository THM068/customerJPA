package com.demo.customer.controllers;

import com.demo.customer.domain.Person;
import com.demo.customer.services.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * Created by tm1c14 on 09/06/2015.
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public Person getPerson(@PathVariable("id")Person person) {
        return person;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Person> getList(Pageable pageable) { //?sort=name,desc&size=4&page=1
        Iterable<Person> iterable = personRepository.findAll(pageable);
        return iterable;
    }

    //Hypermedia support for Pageables
    @RequestMapping(value = "pages", method = RequestMethod.GET, produces = "application/json")
    HttpEntity<PagedResources<Person>> persons(Pageable pageable, PagedResourcesAssembler assembler) {

        Page<Person> persons = personRepository.findAll(pageable);
        ResponseEntity entity = new ResponseEntity(assembler.toResource(persons), HttpStatus.OK);
        return entity;
    }


}
