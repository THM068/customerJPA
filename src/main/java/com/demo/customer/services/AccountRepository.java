package com.demo.customer.services;

import com.demo.customer.domain.Account;
import com.demo.customer.domain.Bookmark;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by tm1c14 on 04/06/2015.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    Collection<Bookmark> findByUsername(String username);
}
