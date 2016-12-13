package com.wine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wine.dto.Foto;
import com.wine.service.CadastroVinhoService;
import com.wine.storage.FotoReader;

@RestController
@RequestMapping("/fotos")
public class FotosController {

	@Autowired
	private CadastroVinhoService cadastroVinhoService;
	
	// required pra quando esse autowired nao for obrigatorio
	@Autowired(required = false)
	private FotoReader fotoReader;
	
	@RequestMapping(value = "{codigo}", method = RequestMethod.POST)
	public Foto upload(@PathVariable Long codigo, @RequestParam("files[]") MultipartFile[] files) {
		
		String url = cadastroVinhoService.salvarFoto(codigo, files[0]);
		return new Foto(url);
	}
	
	// os : separam para colocar a expressao regular .*
	@RequestMapping("/{nome:.*}")
	public byte[] recuperar(@PathVariable String nome) {
		
		return fotoReader.recuperar(nome);
	}
	
}
