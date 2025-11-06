//package tntra.io.pss_server.handler;
//
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageHandler;
//import org.springframework.messaging.MessagingException;
//import org.springframework.messaging.support.GenericMessage;
//
//public class TcpMessageHandler implements MessageHandler {
//    @Override
//    public void handleMessage(Message<?> message) throws MessagingException {
//
//        try{
//            String payload = message.getPayload().toString();
//
//            // temporary
//            System.out.println("Received message from client: " + payload);
//
//            // Prepare a simple acknowledgment response
//            String response = "Message received successfully!";
//
//            // Send response back to the same TCP connection
//            MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
//            if (replyChannel != null) {
//                replyChannel.send(new GenericMessage<>(response));
//                System.out.println("✅ Sent acknowledgment back to client");
//            } else {
//                System.out.println("⚠️ No reply channel found — client may not be expecting response.");
//            }
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//}

package tntra.io.pss_server.handler;


import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class TcpMessageHandler  {

    @ServiceActivator(inputChannel = "inputChannel")
    public String handleMessage(Message<?> message) throws MessagingException {
        try {

            String textPayload = new String((byte[]) message.getPayload());

            // Bytes format to String format
            System.out.println("Received message from client: " + textPayload);

            String response = "Message received successfully!"+textPayload;
            return response;

        } catch (Exception e) {
            throw new MessagingException("Error handling message: " + e.getMessage(), e);
        }
    }
}
