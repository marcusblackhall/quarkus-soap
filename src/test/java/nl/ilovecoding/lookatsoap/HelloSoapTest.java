package nl.ilovecoding.lookatsoap;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class HelloSoapTest {

    @Test
    void shouldRetrieveCity() throws IOException {

         given()
                .auth()
                .preemptive()
                .basic("marcus","marcus12")
                .log().all()
                .body(bodyfFromFile())
                .when().post("/soap/sayhello")
                .then()
                .log().body()
                .assertThat()
                .body(hasXPath("//return"),containsString("Hello Marcus"))
                .statusCode(200).extract().body().asString();

    }

    private String  bodyfFromFile() throws IOException {

        Path path = Path.of("src","test","resources","request.xml");
        return  Files.lines(path).collect(Collectors.joining());


    }
}
