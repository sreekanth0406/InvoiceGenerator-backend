package com.generator.invoice.Utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.generator.invoice.Entities.UserMaster;
import com.generator.invoice.Repository.UserMasterRepository;

public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserMasterRepository userMasterRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserMaster> user =  userMasterRepo.findByEmail(username);
		return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found with name : "+username));
	}

}
