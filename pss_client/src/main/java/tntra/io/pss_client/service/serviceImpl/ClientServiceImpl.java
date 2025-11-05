package tntra.io.pss_client.service.serviceImpl;

import org.springframework.stereotype.Service;
import tntra.io.pss_client.config.TcpClientConfig;
import tntra.io.pss_client.service.ClientService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

@Service
public class ClientServiceImpl implements ClientService {

    TcpClientConfig clientConfig;

    public ClientServiceImpl(TcpClientConfig clientConfig){
        this.clientConfig=clientConfig;
    }

    @Override
    public String sendRequest(String json) throws Exception {

        String host = clientConfig.getHost();
        int port = clientConfig.getPort();

        try(Socket socket = new Socket(host,port)){
            socket.setSoTimeout(10000);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write((json.trim()+"\n").getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8))){
                String response = bufferedReader.readLine();

                return response != null ? response : "";
            }
        }
    }
}