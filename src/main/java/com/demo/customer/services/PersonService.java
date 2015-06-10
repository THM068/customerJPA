package com.demo.customer.services;

import com.demo.customer.domain.Person;
import com.demo.customer.util.CONS;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by tm1c14 on 10/06/2015.
 */
@Component
public class PersonService implements InitializingBean {
    private RestTemplate restTemplate;

    public Person getPerson(Long id) {
        String url = CONS.BASE_URL_PERSON + "/{id}";
        ResponseEntity<Person> responseEntity = restTemplate.getForEntity(url, Person.class, id);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            new RuntimeException("Quick call the cops ...");
        }

        return responseEntity.getBody();
    }

    public void updatePerson(Long id, Map<String,String> params) {
        String url = CONS.BASE_URL_PERSON + "/{id}";
        String name = params.get("name");
        String lastName = params.get("lastName");

        JSONObject  request = new JSONObject();
        request.put("name", name);
        request.put("lastName", lastName);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<String>(request.toString(), httpHeaders);

        ResponseEntity<String> updateResponse = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);

        if(updateResponse.getStatusCode() == HttpStatus.OK) {
            throw new RuntimeException("Call the cops ..............");
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        restTemplate = new RestTemplate();
    }
}
