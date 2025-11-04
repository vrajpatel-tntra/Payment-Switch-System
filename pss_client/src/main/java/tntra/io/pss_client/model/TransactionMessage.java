package tntra.io.pss_client.model;

import lombok.Data;

@Data
public class TransactionMessage {

    public String transactionId;
    public String pan;
    public String amount;
    public String responseCode;
    public String destination;
}
