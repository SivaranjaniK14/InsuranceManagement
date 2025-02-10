package com.application.Controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.application.Exception.ClientNotFoundException;
import com.application.Model.Client;
import com.application.Service.ClientServices;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientServices clientService;
    
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() throws ClientNotFoundException {
        List<Client> found = clientService.getClients();
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Integer id) throws ClientNotFoundException {
        Client found = clientService.getClient(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> newClient(@Valid @RequestBody Client c) {
        Client saved = clientService.newClient(c);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@Valid @RequestBody Client c, @PathVariable("id") Integer id) throws ClientNotFoundException {
        Client saved = clientService.updateClient(id, c);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Integer id) throws ClientNotFoundException {
        clientService.deleteClient(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
    
}