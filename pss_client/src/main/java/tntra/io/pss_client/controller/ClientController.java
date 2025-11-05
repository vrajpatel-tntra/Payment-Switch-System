package tntra.io.pss_client.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tntra.io.pss_client.service.ClientService;

@RestController
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(String jsonRequest){

        try{
            String response = clientService.sendRequest(jsonRequest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
