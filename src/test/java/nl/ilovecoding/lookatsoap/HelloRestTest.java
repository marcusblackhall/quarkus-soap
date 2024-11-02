package nl.ilovecoding.lookatsoap;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


@QuarkusTest
class HelloRestTest {

    @Test
    void shouldRerieveCity(){


        String string = given()
                .when().get("/lookatsoap/rest/api")
                .then()
                .statusCode(200).extract().body().asString();

        System.out.println(string);
        JsonPath path = new JsonPath(string);
        String name = path.getString("name");
        assertEquals("Marcus",name);

    }
  
}