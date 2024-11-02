package nl.ilovecoding.lookatsoap;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@QuarkusTest
@TestHTTPEndpoint(HelloRest.class)
class HelloRestTest {

    @Test
    void shouldRetrieveCity() {

        given()
                .log().all()
                .when().get("/1")
                .then()
                .body("name",equalTo("Marcus"))
                .body("city",equalTo("Amsterdam"))
                .log().body()
                .statusCode(200).extract().body().asString();

    }

}