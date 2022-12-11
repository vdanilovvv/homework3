package ru.otus.petstore;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserQueries {

    public Response postUser(UserDTO userDto, Integer statusCode) {
        return RestAssured.given()
                .spec(UserSpecification.requestSpecification)
                .body(userDto)
                .when()
                .post()
                .then()
                .spec(UserSpecification.responseSpecification(statusCode))
                .extract()
                .response();
    }

    public Response updateUser(String username, UserDTO userDTO, Integer statusCode) {
        return RestAssured.given()
                .spec(UserSpecification.requestSpecification)
                .body(userDTO)
                .when()
                .put("/{username}", username)
                .then()
                .spec(UserSpecification.responseSpecification(statusCode))
                .extract()
                .response();
    }

    public Response getUser(String username, Integer statusCode) {
        return RestAssured.given()
                .spec(UserSpecification.requestSpecification)
                .when()
                .get("/{username}", username)
                .then()
                .spec(UserSpecification.responseSpecification(statusCode))
                .extract()
                .response();
    }

    public Response deleteUser(String username, Integer statusCode) {
        return RestAssured.given()
                .spec(UserSpecification.requestSpecification)
                .when()
                .delete("/{username}", username)
                .then()
                .spec(UserSpecification.responseSpecification(statusCode))
                .extract()
                .response();
    }


}
