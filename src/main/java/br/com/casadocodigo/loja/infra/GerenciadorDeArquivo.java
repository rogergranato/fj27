package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class GerenciadorDeArquivo {

	public String save(String diretorio, MultipartFile sumario) {

		File nomeCompleto= new File(diretorio, sumario.getOriginalFilename());
		
		try {
			sumario.transferTo(nomeCompleto);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nomeCompleto.getPath();
	}

}
