package com.ms.example.product;

import io.micrometer.core.instrument.util.IOUtils;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductControllerIntegrationTest {

    private static final String PRODUCTS_PATH = "/products";

    @Before
    public void setBaseUrl() {
        RestAssured.baseURI = "http://localhost:8080/v1";
    }

    @Test
    public void shouldImportproducts() {
        given()
            .body(jsonFile("products.json"))
            .contentType(JSON)
            .post(PRODUCTS_PATH + "/import")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("size()", equalTo(2));
    }

    @Test
    public void shouldValidateImportRequest() {
        given()
            .body(jsonFile("products-broken.json"))
            .contentType(JSON)
            .post(PRODUCTS_PATH + "/import")
            .then()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .assertThat()
            .body("message", containsString("products[0].name: must not be blank"))
            .body("message", containsString("products[0].createdAt: must not be null"))
            .body("message", containsString("conversations[0].userId: must not be null"))
            .body("message", containsString("conversations[0].fromUser: must not be blank"))
            .body("message", containsString("conversations[0].threads[0].id: must not be null"))
            .body("message", containsString("conversations[0].threads[0].payload: must not be blank"));
    }

    @Test
    public void shouldGetCompanyDetails() {
        given()
            .body(jsonFile(PRODUCTS_PATH + ".json"))
            .contentType(JSON)
            .post(PRODUCTS_PATH + "/import");

        given()
            .get(PRODUCTS_PATH + "/11")
            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("name", equalTo("earrings"))
            .body("conversationCount", equalTo(1))
            .body("mostPopularUserId", equalTo(33));

        given()
                .get(PRODUCTS_PATH + "/12")
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat()
                .body("name", equalTo("headphones"))
                .body("conversationCount", equalTo(0))
                .body("mostPopularUserId", blankOrNullString());
    }

    private String jsonFile(String name) {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(name), StandardCharsets.UTF_8);
    }

}