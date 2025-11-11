package tntra.io.pss_client.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tntra.io.pss_client.service.ClientService;

@RestController
@RequestMapping("/message")
@Tag(name = "Payment Client", description = "APIs for sending transaction requests to TCP server")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/send")
    @Operation(summary = "Send Transaction Message", description = "Sends a JSON transaction request over TCP to the Payment Switch Server.")
    public ResponseEntity<String> sendMessage(@RequestBody String jsonRequest) {

        try {
            String response = clientService.sendRequest(jsonRequest);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            String responseCode = jsonNode.path("responseCode").asText();

            if ("00".equals(responseCode)) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}