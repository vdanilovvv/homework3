package ru.otus.petstore;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class UserUtils {

    static RequestSpecification requestSpecification =
            given()
                    .baseUri("https://petstore.swagger.io/v2")
                    .basePath("/user")
                    .contentType(ContentType.JSON)
                    .log().all();

    public static ResponseSpecification responseSpecification(Integer statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(statusCode)
                .build();
    }

    public static UserDTO userDTO() {
        return UserDTO.builder()
                .username("tester")
                .firstName("Tom")
                .lastName("Vardy")
                .email("tester@gmail.com")
                .phone("88005553535")
                .password("qwerty")
                .build();
    }

    public static Response postUser(UserDTO userDto) {
        return given()
                .spec(requestSpecification)
                .body(userDto)
                .when()
                .post()
                .then()
                .spec(responseSpecification(200))
                .extract()
                .response();
    }

    public static Response getUser(String username) {
        return given()
                .spec(requestSpecification)
                .when()
                .get("/{username}", username)
                .then()
                .spec(responseSpecification(200))
                .extract()
                .response();
    }
}
