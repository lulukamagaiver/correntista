package br.com.unp.uicorrentista.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.unp.uicorrentista.model.Correntista;
import br.com.unp.uicorrentista.service.CorrentistaService;

@Controller
public class CorrentistaController {

	@Autowired
	private CorrentistaService service;

	@GetMapping("/")
	public ModelAndView listar() {
		ModelAndView view = new ModelAndView("ListarCorrentistas");
		view.addObject("correntistas", service.findAll());
		return view;
	}
	
	@GetMapping("/adicionar")
	public ModelAndView add(Optional<Correntista> optional) {
		
		ModelAndView mv = new ModelAndView("AdicionarCorrentista");
		mv.addObject("correntista", optional);
		
		return mv;
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Optional<Correntista> optional, BindingResult result) {		
		
		if (optional.get().getDataCriacao() == null) {
			optional.get().setDataCriacao(new Date());
		}
		
		service.save(optional.get());
		service.findAll();
		return "redirect:/";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		return add(service.findOne(id));
	}

	@GetMapping("/deletar/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		service.findAll();
		return "redirect:/";
	}
}
