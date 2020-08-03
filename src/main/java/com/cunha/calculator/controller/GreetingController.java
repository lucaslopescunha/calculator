package com.cunha.calculator.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cunha.calculator.exception.ResourceNotFoundException;
import com.cunha.calculator.model.Greeting;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double greeting(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		if("1".equals(numberOne)) {
			throw new ResourceNotFoundException("Please set a numeric VALUE!");
		}
		return Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);
	}

}
