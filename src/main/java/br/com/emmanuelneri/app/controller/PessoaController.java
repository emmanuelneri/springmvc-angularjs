package br.com.emmanuelneri.app.controller;

import br.com.emmanuelneri.app.model.Pessoa;
import br.com.emmanuelneri.app.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @RequestMapping(path = "/listar", method = RequestMethod.GET)
    public ResponseEntity listar() {
        return ResponseEntity.ok(pessoaService.findAll());
    }

    @RequestMapping(path = "/buscar", method = RequestMethod.GET)
    public ResponseEntity listarPessoas(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pessoaService.findById(id));
    }

    @RequestMapping(path = "cadastrar", method = RequestMethod.POST)
    public ResponseEntity salvar(@RequestBody Pessoa Pessoa)  {
        pessoaService.save(Pessoa);
        return ResponseEntity.ok().build();
    }
}
