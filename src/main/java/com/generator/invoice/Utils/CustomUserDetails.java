package com.generator.invoice.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generator.invoice.Entities.UserMaster;

public class CustomUserDetails implements UserDetails{
		
	private String userName;
	private String password;
	private String role;
	

	public CustomUserDetails(UserMaster user) {
		super();
		this.userName = user.getEmail();
		this.password = user.getPassword();
		this.role = user.isAdmin() ? Constants.ROLE_ADMIN : Constants.ROLE_USER;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(Constants.ROLE_PREFIX + role));

        return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

}