package org.sid.service;

import org.sid.domain.Compte;
import org.sid.domain.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueService {

	public Compte consulterCompte(String codeCompte);
	public void verser(String codeCompte , double montant);
	public void retirer(String codeCompte,double montant);
	public void virer(String codeCompte1,String codeCompte2,double montant);
	public Page<Operation> listOperations(String codeCompte,int page,int size);
}
