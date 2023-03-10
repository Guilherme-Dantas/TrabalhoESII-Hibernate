package pucminas.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import pucminas.demo.entities.Contato;

import java.util.List;

public interface ContatoRepository extends CrudRepository<Contato, Integer> {
    List<Contato> findAll();
}
