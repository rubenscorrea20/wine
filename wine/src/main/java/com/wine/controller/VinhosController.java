package com.wine.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wine.model.TipoVinho;
import com.wine.model.Vinho;
import com.wine.repository.Vinhos;
import com.wine.service.CadastroVinhoService;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {

	@Autowired
	private Vinhos vinhos;
	
	@Autowired
	private CadastroVinhoService cadastroVinhoService;
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/vinho/ListagemVinhos");
		mv.addObject("vinhos", vinhos.findAll());
		return mv;
	}
	
	//Cria o mapeamento para a view passando o objeto vinho para o campo do formulario utilizar
	@RequestMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView mv = new ModelAndView("/vinho/CadastroVinho");
		//Passa o tipo de vinho para a View passando o array da enum TipoVinho.values()
		mv.addObject("tipos", TipoVinho.values());
		return mv;
	}
	
	// Salva um vinho no banco, caso não haja erros, ele redireciona para a página /vinhos/novo
	// RedirectAttributes para passar uma mensagem no redirecionamento
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes) {
		
		// se o resultado tiver algum erro, ele retorna o metodo "novo" mas mantem os campos
		// preenchidos
		if (result.hasErrors()) {
			return novo(vinho);
		}
		cadastroVinhoService.salvar(vinho);
		attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso!");
		return new ModelAndView("redirect:/vinhos/novo");
	}
	
	// Configuracao na WebConfig para que o Spring reconheça o Vinho pelo id sem precisar do FindOne
	@RequestMapping("/{codigo}")
	public ModelAndView visualizar(@PathVariable("codigo") Vinho vinho) {
		ModelAndView mv = new ModelAndView("vinho/VisualizacaoVinho");
		//Vinho vinho = vinhos.findOne(codigo);
		mv.addObject("vinho", vinho);
		return mv;
	}
	
}

















