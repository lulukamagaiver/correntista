package br.com.unp.uicorrentista.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.unp.uicorrentista.model.Correntista;
import br.com.unp.uicorrentista.model.Movimentacao;
import br.com.unp.uicorrentista.service.CorrentistaService;
import br.com.unp.uicorrentista.service.MovimentacaoService;

@Controller
@RequestMapping("/movimentacao")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoService movimentacaoService;
	
	@Autowired
	private CorrentistaService correntistaService;
	
	@GetMapping("/")
	public ModelAndView listar() {
		ModelAndView view = new ModelAndView("MovimentaSaldo");
		view.addObject("correntistas", correntistaService.findAll());
		view.addObject("listMovimentacao", movimentacaoService.findAll());
		view.addObject("movimentacao", new Movimentacao());
		return view;
	}
	
	@PostMapping()
	public ModelAndView salvar(@Valid Optional<Movimentacao> optional, BindingResult result) {		
		
		if (optional.get().getDataCriacao() == null) {
			optional.get().setDataCriacao(new Date());
		}
		
		movimentacaoService.save(optional.get());
		return listar();
	}
}
