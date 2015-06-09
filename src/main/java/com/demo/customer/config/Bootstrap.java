package com.demo.customer.config;

import com.demo.customer.domain.Account;
import com.demo.customer.domain.Bookmark;
import com.demo.customer.domain.Customer;
import com.demo.customer.domain.Person;
import com.demo.customer.services.AccountRepository;
import com.demo.customer.services.BookmarkRepository;
import com.demo.customer.services.CustomerRepository;
import com.demo.customer.services.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by tm1c14 on 04/06/2015.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private PersonRepository personRepository;



    @Override
    public void run(String... args) throws Exception {
        //save a couple of customers
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));

        for(Customer customer: customerRepository.findAll()) {
            System.out.println("Customer found with findOne(1L):");
            System.out.println("--------------------------------");
            System.out.println(customer);
            System.out.println();
        }

        //fetch customer by lastName
        System.out.println("Customer found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        for(Customer bauer: customerRepository.findByLastName("Bauer")) {
            System.out.println(bauer);
        }

        String [] accountNames = { "jhoeller", "dsyer,pwebb", "ogierke", "rwinch", "mfisher", "mpollack", "jlong" };

        for(String accountName: accountNames) {
            Account account = accountRepository.save(new Account(accountName, "some-password"));
            bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/1/" + accountName, "A description"));
            bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/2/" + accountName, "A description"));
        }

        System.out.println("Bookmarks found :");
        System.out.println("--------------------------------------------");
        for(Bookmark bookmark: bookmarkRepository.findAll()) {
            System.out.println(bookmark);
        }

        String [] people = { "thando.mafela", "kerrie.washington", "charlene.waters", "tupac.shakur"};
        System.out.println("--------------Building people list");
        for(String person: people) {
            String [] array = person.split("\\.");
            String name = array[0];
            String lastName = array[1];

            Person p = new Person(name,lastName);
            personRepository.save(p);
        }

        System.out.println("--------------Display people list");
        for(Person p : personRepository.findAll()) {
            System.out.println(p);
        }
    }
}
