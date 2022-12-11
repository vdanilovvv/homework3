package ru.otus.petstore;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {


    @Test
    public void testCreateUser() {
        UserQueries uq = new UserQueries();

        UserDTO user = UserUtils.userDTO();
        uq.postUser(user, 200);
        Response response = uq.getUser(user.getUsername(), 200);
        Assertions.assertEquals(response.getBody().as(UserDTO.class), user);
        uq.deleteUser(user.getUsername(), 200);
        uq.getUser(user.getUsername(), 404);

    }

    @Test
    public void testUpdateUser() {
        UserQueries uq = new UserQueries();

        UserDTO user1 = UserUtils.userDTO();
        uq.postUser(user1, 200);
        UserDTO user2 = UserUtils.userDTO();
        uq.updateUser(user1.getUsername(), user2, 200);
        Response response = uq.getUser(user2.getUsername(), 200);
        Assertions.assertEquals(response.getBody().as(UserDTO.class), user2);
        uq.deleteUser(user2.getUsername(), 200);
        uq.getUser(user2.getUsername(), 404);

    }

    @Test
    public void testGetNotExistUser() {
        UserQueries uq = new UserQueries();

        Response response = uq.getUser("notexistName", 404);
        Assertions.assertEquals(response.jsonPath().get("message"), "User not found");
    }

    @Test
    public void testDeleteNotExistUser() {
        UserQueries uq = new UserQueries();

        uq.deleteUser("notexistName", 404);
    }
}
