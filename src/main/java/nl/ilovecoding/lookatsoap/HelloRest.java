package nl.ilovecoding.lookatsoap;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Path("/rest/api")
public class HelloRest {

    List<Person> PERSONS = List.of(
            new Person(1, "Marcus", "Amsterdam"),
            new Person(2, "John", "Leiden")
    );


    @GET
    public List<Person> getPersons() {
        return PERSONS;
    }

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") Integer id) {

        Optional<Person> personForId = PERSONS.stream().filter(p -> p.id().equals(id)).findFirst();
        if (personForId.isPresent()) {
            return Response.ok().entity(personForId.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("Id not found")).build();
    }

    record ErrorMessage(String message) {
    }

}