package com.demo.customer.services;

import com.demo.customer.domain.Bookmark;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by tm1c14 on 04/06/2015.
 */
public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
    Collection<Bookmark> findByAccountUsername(String username);
}

