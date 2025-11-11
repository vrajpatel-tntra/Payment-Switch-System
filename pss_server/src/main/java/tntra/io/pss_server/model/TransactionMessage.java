package tntra.io.pss_server.model;

public class TransactionMessage {

    private String transactionId;
    private String pan;
    private String amount;
    private String responseCode;
    private String destination;

    public TransactionMessage() {
    }

    // For incoming client data
    public TransactionMessage(String transactionId, String pan, String amount) {
        this.transactionId = transactionId;
        this.pan = pan;
        this.amount = amount;
    }

    // For server-side full construction
    public TransactionMessage(String transactionId, String pan, String amount, String responseCode, String destination) {
        this.transactionId = transactionId;
        this.pan = pan;
        this.amount = amount;
        this.responseCode = responseCode;
        this.destination = destination;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
