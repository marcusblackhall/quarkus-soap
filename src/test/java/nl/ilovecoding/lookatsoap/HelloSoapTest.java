package nl.ilovecoding.lookatsoap;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasXPath;

@QuarkusTest
public class HelloSoapTest {

    @TestHTTPResource("/lookatsoap/soap/sayhello")
    URL soapbase;

    @Test
    void shouldRetrieveCity() throws IOException {

        given()
                .auth()
                .preemptive()
                .basic("marcus", "marcus12")
                .log().all()
                .body(bodyfFromFile())
                .when().post(soapbase)
                .then()
                .log().body()
                .assertThat()
                .body(hasXPath("//return"), containsString("Hello Marcus"))
                .statusCode(200).extract().body().asString();

    }

    private String bodyfFromFile() throws IOException {

        Path path = Path.of("src", "test", "resources", "request.xml");
        return Files.lines(path).collect(Collectors.joining());


    }
}
