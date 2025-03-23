package nl.ilovecoding.lookatsoap;

import io.quarkus.logging.Log;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PayloadInterceptor extends AbstractPhaseInterceptor<Message> {

    public PayloadInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        Log.info("In payload interceptor");
        InputStream content = message.getContent(InputStream.class);
        try {
            ByteArrayOutputStream copy = new ByteArrayOutputStream();
            content.transferTo(copy);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(copy.toByteArray());
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(null, inputStream);

            var soapBody = soapMessage.getSOAPBody();

            Log.info("Text body " + soapBody.getTextContent());
            ByteArrayOutputStream bodyOutput = new ByteArrayOutputStream();
            String soapBodyContent = new String(bodyOutput.toByteArray(), "UTF-8");
            Log.info("SOAP Body: " + soapBodyContent);

            message.setContent(InputStream.class, new ByteArrayInputStream(copy.toByteArray()));
        } catch (IOException | SOAPException e) {
            throw new Fault(e);
        }
    }


}
