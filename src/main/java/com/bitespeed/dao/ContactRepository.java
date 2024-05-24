package com.bitespeed.dao;

import com.bitespeed.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query("SELECT c FROM Contact c WHERE c.phoneNumber = :phoneNumber OR c.email = :email")
    List<Contact> findByPhoneNumberOrEmail(@Param("phoneNumber") String phoneNumber, @Param("email") String email);
}