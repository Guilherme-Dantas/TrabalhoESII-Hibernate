package pucminas.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pucminas.demo.DTOs.ContatoDTO;
import pucminas.demo.services.ContatoService;

@Controller
@RequestMapping("/api/contato")
public class ContatoController {

    @Autowired
    ContatoService contatoService;

    @PostMapping
    public ResponseEntity<Object> createContato(@RequestBody ContatoDTO contatoDTO){
        try{
            contatoService.createContato(contatoDTO);
            ResponseEntity responseEntity = new ResponseEntity("Criado com sucesso", HttpStatus.CREATED);
            return responseEntity;
        }catch (Exception e){
            ResponseEntity responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }
}
