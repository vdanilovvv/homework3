package ru.otus.petstore;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserSpecification {
    static RequestSpecification requestSpecification =
            RestAssured.given()
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
}
