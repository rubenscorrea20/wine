package com.wine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wine.dto.Foto;

@RestController
@RequestMapping("/fotos")
public class FotosController {

	@RequestMapping(method = RequestMethod.POST)
	public Foto upload(@RequestParam("files[]") MultipartFile[] files) {
		return new Foto(files[0].getOriginalFilename());
	}
	
}
