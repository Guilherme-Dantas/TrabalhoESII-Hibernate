package pucminas.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pucminas.demo.DTOs.ContatoDTO;
import pucminas.demo.entities.Contato;
import pucminas.demo.services.ContatoService;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/contato")
public class ContatoController {

    @Autowired
    ContatoService contatoService;

    @GetMapping
    public ResponseEntity<Object> getAllContatos(){
        try{
            List<Contato> contatoList = contatoService.getAllContatoAsList();
            ResponseEntity<Object> responseEntity = new ResponseEntity(contatoList, HttpStatus.OK);
            return responseEntity;
        }catch (Exception e){
            ResponseEntity responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getContatoById(@PathVariable("id") int id){
        try{
            Contato contato = contatoService.getContatoById(id);
            if(contato == null){
                ResponseEntity responseEntity = new ResponseEntity("Contato com id " + id + " não encontrdo", HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            ResponseEntity<Object> responseEntity = new ResponseEntity(contato, HttpStatus.OK);
            return responseEntity;
        }catch (Exception e){
            ResponseEntity responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }


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


    @PostMapping("/{id}")
    public ResponseEntity<Object> updateContato(@PathVariable("id") int id, @RequestBody ContatoDTO contatoDTO){
        try{
            Contato contato = contatoService.updateContato(id, contatoDTO);
            if(contato == null){
                ResponseEntity responseEntity = new ResponseEntity("Contato com id " + id + " não encontrdo", HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            ResponseEntity responseEntity = new ResponseEntity(contato, HttpStatus.OK);
            return responseEntity;
        }catch (Exception e){
            ResponseEntity responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContatoById(@PathVariable("id") int id){
        try{
            Contato contato = contatoService.deleteContatoById(id);
            if(contato == null){
                ResponseEntity responseEntity = new ResponseEntity("Contato com id " + id + " não encontrdo", HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            ResponseEntity responseEntity = new ResponseEntity("Deletado com sucesso\n" + contato, HttpStatus.OK);
            return responseEntity;
        }catch (Exception e){
            ResponseEntity responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }
}
