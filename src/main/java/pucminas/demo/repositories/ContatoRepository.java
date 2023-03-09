package pucminas.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import pucminas.demo.entities.Contato;

public interface ContatoRepository extends CrudRepository<Contato, Integer> {
}
