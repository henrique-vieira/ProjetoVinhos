package com.vibratecnologia.vinhos.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vibratecnologia.vinhos.model.TipoVinho;
import com.vibratecnologia.vinhos.model.Vinho;
import com.vibratecnologia.vinhos.repository.Vinhos;
import com.vibratecnologia.vinhos.repository.filter.VinhoFilter;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {
	
	@Autowired //o spring procura qual é a implementação de "vinhos" e injeta essa dependência para não precisar ficar instanciando um novo objeto sempre
	private Vinhos vinhos;/*Vem da Interface repository "Vinhos", que permite ao controlador
	                        realizar as opções de CRUD*/
	
	@GetMapping("/novo")
	public ModelAndView novo(Vinho vinho){
		ModelAndView mv = new ModelAndView("vinho/cadastro-vinho");
		mv.addObject(vinho);
		mv.addObject("tipos", TipoVinho.values());
		return mv; //precisa estar dentro da pasta template
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result,
			RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(vinho);
					
		}
		vinhos.save(vinho); /*O controlador agora pode acessar os métodos de CRUD da interface "Vinhos"*/
		attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso !");
		return new ModelAndView("redirect:/vinhos/novo");
	}
	@GetMapping
	public ModelAndView pesquisar(VinhoFilter vinhoFilter){
		ModelAndView mv = new ModelAndView("/vinho/pesquisa-vinhos");
		mv.addObject("vinhos", vinhos.findByNomeContainingIgnoreCase(
				Optional.ofNullable(vinhoFilter.getNome()).orElse("%")));//se o nome estiver nulo, será passado "%"
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		Vinho vinho = vinhos.findOne(codigo);
		return novo(vinho);
	}
	
	@DeleteMapping("/{codigo}")
	public String apagar(@PathVariable Long codigo, RedirectAttributes attributes){
		vinhos.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Vinho removido com sucesso.");
		return "redirect:/vinhos";
	}
}
