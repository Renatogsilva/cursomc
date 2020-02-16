package br.com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.dto.ClienteDTO;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository _repositoryCliente;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer urId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista.

		Cliente aux = _repositoryCliente.findByEmail(objDto.getEmail());

		if(aux != null && !aux.getId().equals(urId)){
			list.add(new FieldMessage("email", "Já existe um registro cadastrado com este e-mail."));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}
}