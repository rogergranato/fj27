package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Livro;

public class LivroValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Livro.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Livro livro = (Livro) target;
		
		ValidationUtils.rejectIfEmpty(errors, "autor", "campo.obrigatorio");
		ValidationUtils.rejectIfEmpty(errors, "titulo", "campo.obrigatorio.livro.titulo");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "campo.obrigatorio");
		
		if (livro.getNumPaginas() <= 0)
		{
			errors.rejectValue("numPaginas", "typeMismatch.livro.numPages");
		}
	}


}
