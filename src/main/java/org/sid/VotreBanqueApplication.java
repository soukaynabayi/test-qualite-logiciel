package org.sid;

import java.util.Date;

import org.sid.dao.IClientRepository;
import org.sid.dao.ICompteRepository;
import org.sid.dao.IOperationRepository;
import org.sid.domain.Client;
import org.sid.domain.Compte;
import org.sid.domain.CompteCourant;
import org.sid.domain.CompteEpargne;
import org.sid.domain.Operation;
import org.sid.domain.Retrait;
import org.sid.domain.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VotreBanqueApplication implements CommandLineRunner{

	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private IOperationRepository operationRepository;
	@Autowired
	private ICompteRepository compteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(VotreBanqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*Client c1=clientRepository.save( new Client("Taha Alami", "miala69@hotmail.fr"));
		Client c2=clientRepository.save(new Client("Alami Taha","ouafine@gmail.com"));
		
		Compte cpt1=compteRepository.save(new CompteCourant("c1", new Date(), 10000, c1, 60000));
		Compte cpt2=compteRepository.save(new CompteEpargne("c2", new Date(), 10000, c2, 5.5));
		
		operationRepository.save( new Versement(new Date(), 5000, cpt1));
		operationRepository.save(new Retrait(new Date(), 4500, cpt1));
		operationRepository.save(new Versement(new Date(), 6000, cpt1));
		operationRepository.save(new Retrait(new Date(), 1000, cpt2));
		operationRepository.save(new Versement(new Date(), 8000, cpt2));*/
		
	}
}
