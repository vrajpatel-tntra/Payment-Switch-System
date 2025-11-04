package tntra.io.pss_client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayCrLfSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;

@Configuration
@IntegrationComponentScan
public class TcpClientConfig {
    /*
    1) Inbound & Outbound
    2) Connection Factory
    3) outbound gateway
    */


    @Bean
    public MessageChannel clientInboundChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel clientOutboundChannel(){
        return new DirectChannel();
    }

    @Bean
    public TcpNetClientConnectionFactory clientConnectionFactory(){
        TcpNetClientConnectionFactory factory = new TcpNetClientConnectionFactory("localhost",5000);

        ByteArrayCrLfSerializer serializer = new ByteArrayCrLfSerializer();
        factory.setSerializer(serializer);
        factory.setDeserializer(serializer);

        return factory;
    }

//    @Bean
//    public TcpOutboundGateway tcpOutboundGateway() {
//        TcpOutboundGateway gateway = new TcpOutboundGateway();
//
//        gateway.setConnectionFactory(clientConnectionFactory());
//        gateway.(clientOutboundChannel());
//        gateway.setReplyChannel(clientInboundChannel());
//
//        return gateway;
//    }

    @Bean
    public IntegrationFlow tcpClientFlow() {
        return IntegrationFlows
                .from("clientOutboundChannel")
                .handle(Tcp.outboundGateway(clientConnectionFactory()))
                .channel("clientInboundChannel")
                .get();
    }

}
