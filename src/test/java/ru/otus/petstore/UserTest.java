package ru.otus.petstore;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {


    @Test
    public void testCreateUser() {
        UserDTO user = UserUtils.userDTO();
        UserQueries.postUser(user, 200);
        Response response = UserQueries.getUser(user.getUsername(), 200);
        Assertions.assertEquals(response.getBody().as(UserDTO.class), user);
        UserQueries.deleteUser(user.getUsername(), 200);
        UserQueries.getUser(user.getUsername(), 404);

    }

    @Test
    public void testUpdateUser() {
        UserDTO user1 = UserUtils.userDTO();
        UserQueries.postUser(user1, 200);
        UserDTO user2 = UserUtils.userDTO();
        UserQueries.updateUser(user1.getUsername(), user2, 200);
        Response response = UserQueries.getUser(user2.getUsername(), 200);
        Assertions.assertEquals(response.getBody().as(UserDTO.class), user2);
        UserQueries.deleteUser(user2.getUsername(), 200);
        UserQueries.getUser(user2.getUsername(), 404);

    }

    @Test
    public void testGetNotExistUser() {
        Response response = UserQueries.getUser("notexistName", 404);
        Assertions.assertEquals(response.jsonPath().get("message"), "User not found");
    }

    @Test
    public void testDeleteNotExistUser() {
        UserQueries.deleteUser("notexistName", 404);
    }
}
