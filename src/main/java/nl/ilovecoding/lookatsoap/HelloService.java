package nl.ilovecoding.lookatsoap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(name="HelloService" , serviceName = "Hello`Service")
public interface HelloService  {

    @WebMethod(action = "sayhello")
    String hello(String name);
}
