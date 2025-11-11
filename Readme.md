# Payment Switch System (Spring Integration over TCP)

## • Overview

This project implements a **Payment Switch System** that handles transaction messages over **TCP/IP** using **Spring Integration**.  
It simulates a simple **Client–Server** model for payment message exchange.

The system consists of two services:

| Service | Description |
|----------|-------------|
| **pss_client** | Accepts JSON transaction requests (from Postman or UI), sends them to the server via TCP, and displays responses. |
| **pss_server** | Listens for TCP messages, converts JSON to entity objects, performs routing & validation logic, and sends responses back. |

The project uses a **custom lightweight JSON message format** (instead of ISO 8583) for clarity and simplicity.



## • Core Features

 - TCP message handling with Spring Integration  
 - JSON (de)serialization using Jackson  
 - Dynamic routing based on PAN BIN  
 - Validation of transaction data  
 - Bidirectional TCP communication (request–response)  
 - REST + Swagger API for the client side  
 - Modular architecture for extensibility



##  • Project Flow

###  End-to-End Communication Flow

```text
 Postman / Web App (HTTP JSON)] --> pss_client (REST Controller)
                        ↓
      ClientService  TCP Socket --> pss_server
                        ↓
       TcpInboundGateway (Spring Integration)
                        ↓
                TcpMessageHandler
                        ↓
     Deserialize JSON --> TransactionMessage Object
                        ↓
         Validate fields (amount, PAN, etc.)
                        ↓
          Route to destination (BankA / BankB / Default Destination)
                        ↓
               Build response message
                        ↓
      Response JSON --> Sent back to client over TCP
```



## • Project Structure

```text
Payment-Switch-System
│
├── pss_client
│ └── src/main/java/tntra/io/pss_client/
│ ├── config/
│ │ ├── SwaggerConfig.java # Swagger/OpenAPI configuration
│ │ └── TcpClientConfig.java # TCP outbound gateway setup
│ │
│ ├── controller/
│ │ └── ClientController.java # REST endpoint for sending JSON to server
│ │
│ ├── service/
│ │ ├── ClientService.java # Interface defining client communication contract
│ │ └── serviceImpl/
│ │ └── ClientServiceImpl.java # TCP socket communication implementation
│ │
│ └── PssClientApplication.java # Spring Boot main entry point
│
│
├── pss_server
│ └── src/main/java/tntra/io/pss_server/
│ ├── config/
│ │ └── TcpServerConfig.java # Configures TCP inbound gateway
│ │
│ ├── handler/
│ │ └── TcpMessageHandler.java # Processes incoming TCP requests
│ │
│ ├── model/
│ │ └── TransactionMessage.java # Entity class for transaction details
│ │
│ ├── route/
│ │ └── RouterService.java # Maps PAN → destination endpoint
│ │
│ ├── validation/
│ │ └── ValidationService.java # Validates PAN, amount, and transaction data
│ │
│ └── PssServerApplication.java # Spring Boot main entry point
│
│
├── Test/tntra/io/pss_server
│ ├── config/
│ │ └── TcpServerConfigTest.java
│ ├── handler/
│ │ └── TcpMessageHandlerTest.java
│ ├── model/
│ │ └── TransactionMessageTest.java
│ ├── route/
│ │ └── RouterServiceTest.java
│ └── validation/
│ └── ValidationServiceTest.java
│
│
├── UML Diagrams/
│ ├── Payment Switch - Activity Diagram.png
│ ├── Payment Switch - Payment Switch - Sequence Diagram (Success).png
│ ├── Payment Switch - Payment Switch - Sequence Diagram (Validation Failure).png
│ ├── Payment Switch Server - Class Diagram.png
│ 
│
└── 📄 README.md
```

## • Project Setup (Quick Start)

###  Prerequisites

- Java 21
- Maven 3.8+
- Postman (for testing)
- IDE : IntelliJ IDEA / VS Code

### Build & Run
#### 1) Server
cd pss_server
mvn spring-boot:run

#### 2) Client
cd pss_client
mvn spring-boot:run


Test Flow

Use Postman →
**POST :** `http://localhost:8087/api/client/send`



## • Test Flow

###  Step 1: Send Request

**Method:** `POST`  
**URL:** `http://localhost:8087/api/client/send`  
**Headers:**  
`Content-Type: application/json`

**Request Body :**
```json
{
  "transactionId": "TXN1001",
  "pan": "1234567890123456",
  "amount": "500.00"
}
``` 
**Expected Outcome :**
```json
{
  "transactionId": "TXN1001",
  "pan": "1234567890123456",
  "amount": "500.00",
  "destination": "Bank-A"
}
```

## • Swagger (Client Only)

Open browser:
 `http://localhost:8087/api/client/send`  





