package org.sid.service;

import java.util.Date;

import org.sid.dao.ICompteRepository;
import org.sid.dao.IOperationRepository;
import org.sid.domain.Compte;
import org.sid.domain.CompteCourant;
import org.sid.domain.Operation;
import org.sid.domain.Retrait;
import org.sid.domain.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BanqueServiceImpl implements IBanqueService {
	@Autowired
	private ICompteRepository compteRepository;
	@Autowired
	private IOperationRepository operationRepository;
	
	@Override
	public Compte consulterCompte(String codeCompte) {
		Compte compte=compteRepository.findOne(codeCompte);
		
		if(compte==null)
			throw new RuntimeException("Aucun Compte Retrouvé");
		
		return compte;
	}

	@Override
	public void verser(String codeCompte, double montant) {
		Compte compte=compteRepository.findOne(codeCompte);
		compte.setSolde(montant+compte.getSolde());
		compteRepository.save(compte);
		
		Versement vers=new Versement(new Date(), montant, compte);
		operationRepository.save(vers);
		
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		
		Compte compte=compteRepository.findOne(codeCompte);
		double facilite=0;
		if(compte instanceof CompteCourant)
			facilite=((CompteCourant) compte).getDecouvert();
		
		if(facilite+compte.getSolde()<montant)
			throw new RuntimeException("Solde Insuffisant");
		
		compte.setSolde(compte.getSolde()-montant);
		compteRepository.save(compte);
		
		Retrait ret=new Retrait(new Date(), montant, compte);
		operationRepository.save(ret);
	}

	@Override
	public void virer(String codeCompte1, String codeCompte2, double montant) {
		if(codeCompte1.equals(codeCompte2))
			throw new RuntimeException("On ne peux pas virer au meme compte");
		
		retirer(codeCompte1,montant);
		verser(codeCompte2,montant);
	}

	@Override
	public Page<Operation> listOperations(String codeCompte, int page, int size) {
		
		return operationRepository.listOperations(codeCompte, new PageRequest(page, size));
	}

}
