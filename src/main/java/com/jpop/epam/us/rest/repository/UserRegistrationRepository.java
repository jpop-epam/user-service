package com.jpop.epam.us.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpop.epam.us.rest.model.db.UserRegistrationData;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, String>{

}
