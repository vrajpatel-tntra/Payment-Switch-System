package tntra.io.pss_server.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayCrLfSerializer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TcpServerConfig.class)
public class TcpServerConfigTest {

    @Autowired
    private TcpServerConfig config;

    @Autowired
    private TcpNetServerConnectionFactory factory;

    @Autowired
    private TcpInboundGateway gateway;

    @Autowired
    private DirectChannel inputChannel;

    @Test
    void testServerConnectionFactoryConfig() {
        assertThat(factory).isNotNull();
        assertThat(factory.getSerializer()).isInstanceOf(ByteArrayCrLfSerializer.class);
        assertThat(factory.getDeserializer()).isInstanceOf(ByteArrayCrLfSerializer.class);
    }

    @Test
    void testInboundGatewayCreation() {
        assertThat(gateway).isNotNull();
        assertThat(gateway.getRequestChannel()).isSameAs(inputChannel); //  same instance, managed by Spring
    }
}
