package br.com.emmanuelneri.app.repository;

import br.com.emmanuelneri.app.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
