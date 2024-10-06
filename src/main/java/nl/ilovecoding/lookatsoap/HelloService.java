package nl.ilovecoding.lookatsoap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(name="HelloService" , serviceName = "Hello`Service")
public interface HelloService  {

    @WebMethod
    String hello(String name);

}
