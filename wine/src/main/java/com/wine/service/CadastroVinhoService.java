package com.wine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wine.model.Vinho;
import com.wine.repository.Vinhos;

@Service
public class CadastroVinhoService {

	@Autowired
	private Vinhos vinhos;
	
	public void salvar(Vinho vinho) {
		//Escrever regras de negocio
		this.vinhos.save(vinho);
	}
	
}
