package pucminas.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pucminas.demo.DTOs.ContatoDTO;
import pucminas.demo.entities.Contato;
import pucminas.demo.repositories.ContatoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;


    public void createContato(ContatoDTO contatoDTO) {
        Contato contato = new Contato(contatoDTO.getNome(), contatoDTO.getEndereco(), contatoDTO.getTelefone());
        contatoRepository.save(contato);
    }

    public List<Contato> getAllContatoAsList() {
        List<Contato> contatoLinkedList = contatoRepository.findAll();
        return contatoLinkedList;
    }

    public Contato getContatoById(int id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        return (contatoOptional.isPresent()) ? contatoOptional.get() : null;
    }

    public Contato deleteContatoById(int id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if(contatoOptional.isPresent()){
            contatoRepository.delete(contatoOptional.get());
            return contatoOptional.get();
        };

        return null;
    }

    public Contato updateContato(int id, ContatoDTO contatoDto){
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if(contatoOptional.isPresent()){
            Contato contatoUpdated = contatoOptional.get();
            contatoUpdated.setEndereco(contatoDto.getEndereco());
            contatoUpdated.setNome(contatoDto.getNome());
            contatoUpdated.setTelefone(contatoDto.getTelefone());
            contatoRepository.save(contatoUpdated);
            return contatoUpdated;
        }else{
            return null;
        }
    }

}
