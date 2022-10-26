package ru.otus.petstore;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static ru.otus.petstore.UserUtils.userDTO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {


    @Test
    @Order(0)
    public void testCreateUser() {
        UserUtils.postUser(userDTO());
    }

    @Test
    @Order(1)
    public void testCreateUserResponse() {
        Response response = UserUtils.postUser(userDTO());
        Assertions.assertEquals(response.jsonPath().getInt("code"), 200);
        Assertions.assertInstanceOf(String.class, response.jsonPath().get("message"));
    }

    @Test
    @Order(2)
    public void testGetUser() {
        UserUtils.getUser(userDTO().getUsername());
    }

    @Test
    @Order(3)
    public void testCreateGetCompareUser() {
        UserUtils.postUser(userDTO());
        Response response = UserUtils.getUser(userDTO().getUsername());
        Assertions.assertEquals(response.getBody().as(UserDTO.class), userDTO());

    }
}
