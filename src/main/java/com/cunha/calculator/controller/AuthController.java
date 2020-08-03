package com.cunha.calculator.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cunha.calculator.repository.UserRepository;
import com.cunha.calculator.security.AccountCredentialsVO;
import com.cunha.calculator.security.jwt.JwtTokenProvider;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity signin(@RequestBody AccountCredentialsVO vo) {
		var username = vo.getCasa();
		var password = vo.getPessoa();
		
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		var user = userRepository.findByUsername(username);
		
		var token = "";
			
		if(user != null) {
			token = tokenProvider.createToken(username, user.getRoles());
		} else {
			throw new UsernameNotFoundException("Usuário "+ username + " não encontrado.");
		}
		Map<Object, Object> mapModel = new HashMap<Object, Object>();
		mapModel.put("username", username);
		mapModel.put("token", token);
		return ok(mapModel);
	}
}
