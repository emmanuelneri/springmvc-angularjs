package br.com.emmanuelneri.app.service;

import br.com.emmanuelneri.app.model.Pessoa;
import br.com.emmanuelneri.app.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }
}
