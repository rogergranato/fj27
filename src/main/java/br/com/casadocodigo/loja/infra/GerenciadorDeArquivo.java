package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class GerenciadorDeArquivo {
	
	@Autowired
	private HttpServletRequest request;

	public String save(String diretorio, MultipartFile sumario) {

		String path = request.getServletContext().getRealPath("/" + diretorio);
		File nomeCompleto= new File(path, sumario.getOriginalFilename());
		
		try {
			sumario.transferTo(nomeCompleto);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return new File(diretorio, sumario.getOriginalFilename()).getPath();
	}

}
