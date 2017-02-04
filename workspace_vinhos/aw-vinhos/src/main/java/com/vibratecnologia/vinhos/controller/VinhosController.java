package com.vibratecnologia.vinhos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vibratecnologia.vinhos.model.TipoVinho;
import com.vibratecnologia.vinhos.model.Vinho;
import com.vibratecnologia.vinhos.repository.Vinhos;

@Controller
public class VinhosController {
	
	@Autowired //o spring procura qual é a implementação de "vinhos" e injeta essa dependência para não precisar ficar instanciando um novo objeto sempre
	private Vinhos vinhos;/*Vem da Interface repository "Vinhos", que permite ao controlador
	                        realizar as opções de CRUD*/
	
	@GetMapping("/vinhos/novo")
	public ModelAndView novo(Vinho vinho){
		ModelAndView mv = new ModelAndView("vinho/cadastro-vinho");
		mv.addObject("tipos", TipoVinho.values());
		return mv; //precisa estar dentro da pasta template
	}
	
	@PostMapping("/vinhos/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result){
		if(result.hasErrors()){
			return novo(vinho);
					
		}
		vinhos.save(vinho); /*O controlador agora pode acessar os métodos de CRUD da interface "Vinhos"*/
		return new ModelAndView("redirect:/vinhos/novo");
	}
}
