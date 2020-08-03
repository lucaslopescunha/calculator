package com.cunha.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cunha.calculator.converter.DozerConverter;
import com.cunha.calculator.exception.ResourceNotFoundException;
import com.cunha.calculator.model.Pessoa;
import com.cunha.calculator.repository.PersonRepository;
import com.cunha.calculator.vo.PersonVO;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public PersonVO create(PersonVO pessoa) {
		Pessoa person = DozerConverter.parseObject(pessoa, Pessoa.class);
		return DozerConverter.parseObject(personRepository.save(person), PersonVO.class);
	}

	public void deleteById(Long id) {
		personRepository.deleteById(id);
	}

	public PersonVO findById(Long id) {
		Pessoa pessoa = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não encontrado"));
		return DozerConverter.parseObject(pessoa, PersonVO.class);
	}

}
