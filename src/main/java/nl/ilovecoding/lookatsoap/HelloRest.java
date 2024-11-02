package nl.ilovecoding.lookatsoap;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@ApplicationScoped

@Path("rest")
public class HelloRest {
    @Path("/api")
    @GET
    public Person getPerson(){
        return new Person("Marcus","Amsterdam");

    }

}


