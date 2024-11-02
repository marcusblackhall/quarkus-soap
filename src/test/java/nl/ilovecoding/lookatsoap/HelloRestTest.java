package nl.ilovecoding.lookatsoap;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@QuarkusTest
@TestHTTPEndpoint(HelloRest.class)
class HelloRestTest {

    @Test
    void shouldRetrieveCity() {

        String string = given()
                .log().all()
                .when().get("/1")
                .then()
                .log().body()
                .statusCode(200).extract().body().asString();

        JsonPath path = new JsonPath(string);
        String name = path.getString("name");
        String city = path.getString("city");

        assertAll( () ->  assertEquals("Marcus", name),
                () ->  assertEquals("Amsterdam", city)
                );


    }

}