package org.mring.bear;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Tag("Positive_tests")
class PositiveTests {

    @BeforeAll
    static void setup() {
        String uri = System.getProperty("baseURI");
        RestAssured.baseURI = uri != null ? uri : "http://localhost:8091";
    }

    @Test
    @Tag("GET")
    void getInfo() {
        Response response = given()
                .contentType(ContentType.HTML)
                .get("/info")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        String info = "Welcome to Alaska!\n" +
                "This is CRUD service for bears in alaska.\n" +
                "CRUD routes presented with REST naming notation:\n" +
                "\n" +
                "POST\t\t\t/bear - create\n" +
                "GET\t\t\t/bear - read all bears\n" +
                "GET\t\t\t/bear/:id - read specific bear\n" +
                "PUT\t\t\t/bear/:id - update specific bear\n" +
                "DELETE\t\t\t/bear - delete all bears\n" +
                "DELETE\t\t\t/bear/:id - delete specific bear\n" +
                "\n" +
                "Example of ber json: {\"bear_type\":\"BLACK\",\"bear_name\":\"mikhail\",\"bear_age\":17.5}.\n" +
                "Available types for bears are: POLAR, BROWN, BLACK and GUMMY.";
        Assertions.assertEquals(info, response.htmlPath().getString("html.body"));
    }

    @ParameterizedTest
    @MethodSource("org.mring.bear.Utils#dataBearsProviderPositive")
    @Tag("POST")
    void addBear(Bear bear) {
        given()
                .contentType(ContentType.JSON)
                .body(bear)
                .post("/bear")
                .then()
                .statusCode(describedAs(
                        String.format("Status should be 201 for bear : name = %s, age = %s, type = %s",
                                bear.getBear_name(), bear.getBear_age(), bear.getBear_type()), is(201))
                );
    }

    @ParameterizedTest
    @MethodSource("org.mring.bear.Utils#dataBearsProviderPositive")
    @Tag("POST")
    @Tag("GET")
    void getBear(Bear bear) throws JsonProcessingException {

        String bearID = given()
                .contentType(ContentType.JSON)
                .body(bear)
                .post("/bear")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .asString();

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(String.format("/bear/%s", bearID))
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        Assertions.assertNotEquals(response, "null", "empty response");
        ObjectMapper objectMapper = new ObjectMapper();
        Bear responce_bear = objectMapper.readValue(response, Bear.class);
        Utils.compareBears(responce_bear, bear);
    }

    @Test
    @Tag("POST")
    @Tag("DELETE")
    @Tag("GET")
    void deleteAllBears() {
        given()
                .contentType(ContentType.JSON)
                .body(Utils.createBear("Bear_delete1", "11", BearTypes.POLAR.toString()))
                .post("/bear")
                .then()
                .statusCode(201);

        given()
                .contentType(ContentType.JSON)
                .body(Utils.createBear("Bear_delete2", "11", BearTypes.POLAR.toString()))
                .post("/bear")
                .then()
                .statusCode(201);

        given()
                .contentType(ContentType.JSON)
                .delete("/bear")
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .get("/bear")
                .then()
                .assertThat()
                .body(Matchers.equalTo("[]"));
    }

    @Test
    @Tag("POST")
    @Tag("DELETE")
    @Tag("GET")
    void getAllBears() throws JsonProcessingException {
        given()
                .contentType(ContentType.JSON)
                .delete("/bear")
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .body(Utils.createBear("Bear_count1", "11", BearTypes.POLAR.toString()))
                .post("/bear")
                .then()
                .statusCode(201);

        given()
                .contentType(ContentType.JSON)
                .body(Utils.createBear("Bear_count1", "11", BearTypes.POLAR.toString()))
                .post("/bear")
                .then()
                .statusCode(201);

        String response = given()
                .contentType(ContentType.JSON)
                .get("/bear")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        ObjectMapper mapper = new ObjectMapper();
        List<Bear> bears = mapper.readValue(response, new TypeReference<List<Bear>>() {
        });
        Assertions.assertEquals(2, bears.size(), "Bears count should be = 2");
//                .body("size()", is(2));
    }

    @Test
    @Tag("POST")
    @Tag("DELETE")
    @Tag("GET")
    void deleteSpecificBear() {
        String bearID = given()
                .contentType(ContentType.JSON)
                .body(Utils.createBear("Bear_count1", "11", BearTypes.POLAR.toString()))
                .post("/bear")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .asString();

        given()
                .contentType(ContentType.JSON)
                .get(String.format("/bear/%s", bearID))
                .then()
                .assertThat()
                .body(not(equalTo("EMPTY")));

        given()
                .contentType(ContentType.JSON)
                .delete(String.format("/bear/%s", bearID))
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .get(String.format("/bear/%s", bearID))
                .then()
                .assertThat()
                .body(Matchers.equalTo("EMPTY"));

    }

    @Test
    @Tag("POST")
    @Tag("PUT")
    @Tag("GET")
    void changeBear() throws JsonProcessingException {
        String bearID = given()
                .contentType(ContentType.JSON)
                .body(Utils.createBear("Bear_old", "1", BearTypes.POLAR.toString()))
                .post("/bear")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .asString();

        Bear newBear = Utils.createBear("Bear_new", "2", BearTypes.BLACK.toString());
        given()
                .contentType(ContentType.JSON)
                .body(newBear)
                .put(String.format("/bear/%s",bearID))
                .then()
                .statusCode(200);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(String.format("/bear/%s", bearID))
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Assertions.assertNotEquals(response, "null", "empty response");
        ObjectMapper objectMapper = new ObjectMapper();
        Bear responce_bear = objectMapper.readValue(response, Bear.class);
        Utils.compareBears(responce_bear, newBear);
    }

}
