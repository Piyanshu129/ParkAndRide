package com.park.ride.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.park.ride.model.User;
import com.park.ride.model.UserPrincipal;
import com.park.ride.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	  @Autowired
	    private UserRepository userRepo;


	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepo.findByName(username);
	        if (user == null) {
	            System.out.println("User Not Found");
	            throw new UsernameNotFoundException("user not found");
	        }
	        
	        return new UserPrincipal(user);
	    }

}
