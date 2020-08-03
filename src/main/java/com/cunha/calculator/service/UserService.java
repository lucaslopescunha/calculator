package com.cunha.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cunha.calculator.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userRepository.findByUsername(username);
		if(userDetails != null) {
			return userDetails;
		} else {
			throw new UsernameNotFoundException("Usuário "+username+ " não encontrado.");
		}
	}

	
}
