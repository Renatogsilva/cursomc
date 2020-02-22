package br.com.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.exceptions.ObjectNotFoundException;
import br.com.cursomc.repositories.ClienteRepository;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository _repositoryCliente;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEnconder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Cliente cliente = _repositoryCliente.findByEmail(email);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(bCryptPasswordEnconder.encode(newPass));
		
		_repositoryCliente.save(cliente);
		
		emailService.sendNewPasswordEmail(cliente, newPass);
	}
	
	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}
	
	private char randomChar() {
		int opt	= random.nextInt(3);
		if(opt == 0) {
			return (char) (random.nextInt(10) + 48);
		}
		else if(opt == 1) {
			return (char) (random.nextInt(26) + 65);
		}else {
			return (char) (random.nextInt(26) + 97);
		}
	}
}
