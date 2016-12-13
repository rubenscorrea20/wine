package com.wine.storage;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("storage-local")
@Component
public class FotoStorageLocal implements FotoStorage, FotoReader {

	private Path local;
	
	public FotoStorageLocal() {
		// Cria um caminho .winefotos na HOME do usuário
		this.local = getDefault().getPath(System.getenv("HOME"), ".winefotos");

		// cria o diretório 
		try {
			Files.createDirectories(this.local);
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
		}
	}
	
	@Override
	public String salvar(MultipartFile foto) {
		String nomeFoto = foto.getOriginalFilename();
		
		// pega a foto passa um file falando onde quer salvar a foto no local criado acima 
		// (separador pega o separador do sistema /linux \windows)
		// salva e retorna o nome da foto
		try {
			foto.transferTo(new File(
					this.local.toAbsolutePath().toString() + getDefault().getSeparator() + nomeFoto));
		} catch (IOException e) {
			throw new RuntimeException("Erro salvando foto", e);
		}
		
		return nomeFoto;
	}

	@Override
	public String getUrl(String nomeFoto) {
		
		return "http://localhost:8080/fotos/" + nomeFoto;
	}

	@Override
	public byte[] recuperar(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro recuperando a foto", e);
		}
		
	}

}
