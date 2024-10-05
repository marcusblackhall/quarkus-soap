package nl.ilovecoding.lookatsoap;

import io.quarkus.logging.Log;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;


import javax.xml.namespace.QName;
import java.util.Set;

public class MessageHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public boolean handleMessage(SOAPMessageContext soapMessageContext) {
        Log.info("In message handler");

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        return false;
    }

    @Override
    public void close(MessageContext messageContext) {

    }

    @Override
    public Set<QName> getHeaders() {
        return Set.of();
    }
}
