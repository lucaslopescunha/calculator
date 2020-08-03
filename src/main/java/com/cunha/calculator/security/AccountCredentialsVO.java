package com.cunha.calculator.security;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountCredentialsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pessoa;
	
	private String casa;

	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((casa == null) ? 0 : casa.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountCredentialsVO other = (AccountCredentialsVO) obj;
		if (casa == null) {
			if (other.casa != null)
				return false;
		} else if (!casa.equals(other.casa))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}
	
}
