package com.oleg.nee.somerestapiproject.repository;

import com.oleg.nee.somerestapiproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
