package com.cunha.calculator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cunha.calculator.converter.DozerConverter;
import com.cunha.calculator.model.Pessoa;
import com.cunha.calculator.vo.PersonVO;


@SpringBootTest
public class DozerConverterTest {

	@Test
	public void conversaoTest() {
		PersonVO personVO = new PersonVO(1l, "Lucas");
		Pessoa pessoa = DozerConverter.parseObject(personVO, Pessoa.class);
		Assert.assertEquals(pessoa.getName(), personVO.getName());
	}
}
