package com.demo.customer.controllers;

import com.demo.customer.domain.Person;
import com.demo.customer.services.PersonRepository;
import com.demo.customer.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * Created by tm1c14 on 09/06/2015.
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public Person getPerson(@PathVariable("id")Person person) {
        return person;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "list", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public Iterable<Person> getList(Pageable pageable) { //?sort=name,desc&size=4&page=1
        Iterable<Person> iterable = personRepository.findAll(pageable);
        return iterable;
    }

    //Hypermedia support for Pageables
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "pages", method = RequestMethod.GET, headers = {"Accept=application/json"})
    HttpEntity<PagedResources<Person>> persons(Pageable pageable, PagedResourcesAssembler assembler) {

        Page<Person> persons = personRepository.findAll(pageable);
        ResponseEntity entity = new ResponseEntity(assembler.toResource(persons), HttpStatus.OK);
        return entity;
    }

    //add a person
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, headers = {"Accept=application/json", "Content-type=application/json"} , consumes = "application/json")
    public Person create(@RequestBody Person person,  HttpServletResponse response)  {
        Person p = personRepository.save(person);
        response.setHeader("Location", "/person/"+ p.getId());

        return p;
    }

    @RequestMapping(value="findperson/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getRestPerson(@PathVariable long id) {
        Person person = personService.getPerson(id);

        return  person;
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.OK)
    public Person updatePerson(@PathVariable("id") Person person, @RequestBody Person newPerson) {
        if(person!=null && personRepository.exists(person.getId() ) ){
            person.setName(newPerson.getName());
            person.setLastName(newPerson.getLastName());
            person = personRepository.save(person);
        }

        return person;
    }

}
