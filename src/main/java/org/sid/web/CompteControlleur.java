package org.sid.web;

import org.sid.domain.Compte;
import org.sid.domain.Operation;
import org.sid.service.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompteControlleur {

	@Autowired
	private IBanqueService banqueService;
	
	@RequestMapping("/index")
	public String index()
	{
		return "VueGlobal";
	}
	
	@RequestMapping("/consultation")
	public String consultation(Model model ,String codeCompte)
	{
		try {
			Compte compte = banqueService.consulterCompte(codeCompte);
			model.addAttribute("compte",compte);
			
			Page<Operation> listDesOperations =banqueService.listOperations(codeCompte, 0, 4);
			model.addAttribute("listDesOperations", listDesOperations.getContent());
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return"VueGlobal";
	}
	@RequestMapping("/operation")
	public String operation(Model model ,String codeCompte,String codeCompte2,String type_operation,double montant)
	{
		try{
			
			if(type_operation.equals("vers"))
				banqueService.verser(codeCompte, montant);
			else if(type_operation.equals("vir"))
				banqueService.virer(codeCompte, codeCompte2, montant);
			else
				banqueService.retirer(codeCompte, montant);
			
		} catch (Exception e)
		{
			model.addAttribute("exception", e);
			return "redirect:/consultation?codeCompte="+codeCompte+"&exception="+e.getMessage();
		}
		
		return "redirect:/consultation?codeCompte="+codeCompte;
	}
}
