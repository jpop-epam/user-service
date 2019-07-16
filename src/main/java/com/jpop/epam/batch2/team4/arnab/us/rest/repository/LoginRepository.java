package com.jpop.epam.batch2.team4.arnab.us.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpop.epam.batch2.team4.arnab.us.rest.model.db.LoginData;

@Repository
public interface LoginRepository extends JpaRepository<LoginData, String>{

}
