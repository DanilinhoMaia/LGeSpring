package br.com.lgespring.lgespringproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.lgespring.lgespringproject.model.Projeto;
import br.com.lgespring.lgespringproject.repository.ProjectRepository;

@Controller
public class MainController {

	@Autowired
	ProjectRepository meuRespository;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/deletarProjetos")
	public String deletarProjetos(Model model) {
		model.addAttribute("message", "@DaniloMaia2021");
		Projeto p = new Projeto();
		model.addAttribute("projeto", p);
		return "formDeletar";
	}
	
	@GetMapping("/listaProjetos")
	public String listaProjetos(Model model) {
		List<Projeto> projetos = meuRespository.findAll();
		Projeto proj = new Projeto();
		model.addAttribute("projetos", projetos);
		model.addAttribute("proj", proj);
		model.addAttribute("message", "@DaniloMaia2021");
		return "listaProjetos";
	}
	
	
	@GetMapping("/formularioInsercao")
	public String formularioInsercao(Model model) {
		model.addAttribute("message", "@DaniloMaia2021");
		Projeto p = new Projeto();
		model.addAttribute("projeto", p);
		return "formInserir";
	} 
	
	@PostMapping("/inserirProjeto")
	public String inserirProjeto(@ModelAttribute Projeto projeto ) {
		Projeto p = new Projeto(projeto.getNome(), projeto.getRisco(), projeto.getValor(), projeto.getParticipantes(), projeto.getDataInicio(),projeto.getDataFim());
		meuRespository.save(p);
		
		return "index";
	} 
	
	@PostMapping("/deletarProjeto")
	public String deletarProjeto(@ModelAttribute("projeto") Projeto projeto) {
		meuRespository.deleteById(projeto.getId());
		
		return "index";
	} 
	
	@GetMapping("/deletarProjetoGET/{id}")
	public String deletarProjetoGET(@PathVariable long id) {
		meuRespository.deleteById(id);
		return "index";
	} 
	
	@GetMapping("/simularProjetoGET/{valor}/{risco}/{id}")
	public String deletarProjetoGET(@PathVariable float risco,@PathVariable float valor, @PathVariable Long id, Model model) {
		if(risco==0.0 ) {
			model.addAttribute("retorno",valor*0.05);
		}else if(risco==1.0 ){
			model.addAttribute("retorno",valor*0.1);
		}else if(risco==2.0 ){
			model.addAttribute("retorno",valor*0.2);
		}
		
		model.addAttribute("message", "@DaniloMaia2021");
		return "simular";
	} 
	
}
