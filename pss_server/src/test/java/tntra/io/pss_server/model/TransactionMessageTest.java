package tntra.io.pss_server.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionMessageTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        TransactionMessage msg = new TransactionMessage(
                "TXN001",
                "1234567890123456",
                "500.00",
                "00",
                "Bank-A"
        );

        assertThat(msg.getTransactionId()).isEqualTo("TXN001");
        assertThat(msg.getPan()).isEqualTo("1234567890123456");
        assertThat(msg.getAmount()).isEqualTo("500.00");
        assertThat(msg.getResponseCode()).isEqualTo("00");
        assertThat(msg.getDestination()).isEqualTo("Bank-A");
    }
}
