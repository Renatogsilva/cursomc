package br.com.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.cursomc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public ClienteNewDTO() {
	}
	
	@NotEmpty(message = "É necessário infomar o nome do cliente.")
	@Length(min = 5, max = 120, message = "O tamanho máximo de caractere permitido deve estar entre 5 e 120.")
	private String nome;
	
	@NotEmpty(message = "É necessário infomar o e-mail")
	@Email(message = "E-mal está inválido.")
	private String email;
	
	@NotEmpty(message = "É necessário informar o CPF/CNPJ")
	private String cpfOuCnpj;
	private Integer tipoCliente;
	
	@NotEmpty(message = "É necessário infomar o logradouro")
	private String logradouro;
	
	@NotEmpty(message = "É necessário infomar o número")
	private String numero;
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = "É necessário infomrar o CEP")
	private String cep;
	
	@NotEmpty(message = "É necessário informar ao menos um telefone.")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;

	@NotEmpty
	private String senha;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}