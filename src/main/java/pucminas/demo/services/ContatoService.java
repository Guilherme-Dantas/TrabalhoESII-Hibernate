package pucminas.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pucminas.demo.DTOs.ContatoDTO;
import pucminas.demo.entities.Contato;
import pucminas.demo.repositories.ContatoRepository;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;


    public void createContato(ContatoDTO contatoDTO){
        Contato contato = new Contato(contatoDTO.getNome(), contatoDTO.getEndereco(), contatoDTO.getTelefone());
        contatoRepository.save(contato);
    }
}
