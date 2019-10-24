package org.sid.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	@Id @GeneratedValue
	private Long codeClient;
	private String nomClient;
	private String email;
	@OneToMany(mappedBy="client")
	private Collection<Compte>comptes;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String nomClient, String email) {
		super();
		this.nomClient = nomClient;
		this.email = email;
	}

	public Long getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(Long codeClient) {
		this.codeClient = codeClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	
	

}
