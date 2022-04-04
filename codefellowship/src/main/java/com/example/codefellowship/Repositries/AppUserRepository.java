package com.example.codefellowship.Repositries;

import com.example.codefellowship.Models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserRepository extends JpaRepository<ApplicationUser, Integer> {
      ApplicationUser findByUsername(String username);

}