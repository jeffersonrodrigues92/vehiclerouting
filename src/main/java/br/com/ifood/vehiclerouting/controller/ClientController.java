package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.entity.Client;
import br.com.ifood.vehiclerouting.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody Client client){
        clientService.save(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Long> update(@RequestBody Client client){
        Long id = clientService.update(client);
        if(Objects.nonNull(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client>getClient(@PathVariable(value = "id") Long id){
        Client client = clientService.get(id);
        if(Objects.nonNull(client)){
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        }
        return new ResponseEntity<Client>(client, HttpStatus.NOT_FOUND);
    }
}
