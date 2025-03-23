package nl.ilovecoding.lookatsoap;

import jakarta.annotation.security.RolesAllowed;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.apache.cxf.interceptor.InInterceptors;

@InInterceptors( interceptors = {"nl.ilovecoding.lookatsoap.PayloadInterceptor"})
@WebService(serviceName = "HelloService")
@RolesAllowed("admin")
public class HelloServiceImpl implements HelloService {

    @Override
    @WebMethod
    public String hello(String name) {
        return String.format("Hello %s", name);

    }

}
