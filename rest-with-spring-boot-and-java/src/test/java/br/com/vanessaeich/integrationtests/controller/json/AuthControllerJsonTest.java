package br.com.vanessaeich.integrationtests.controller.json;

import br.com.vanessaeich.configs.TestConfigs;
import br.com.vanessaeich.integrationtests.dtos.AccountCredencialsDTO;
import br.com.vanessaeich.integrationtests.dtos.TokenDTO;
import br.com.vanessaeich.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Vanessa Eich on 03/02/2023
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerJsonTest extends AbstractIntegrationTest {

    private static TokenDTO tokenDTO;

    @Test
    @Order(1)
    public void testSignin() {

        AccountCredencialsDTO user = new AccountCredencialsDTO("leandro", "admin123");
        tokenDTO = given()
                .basePath("/auth/signin")
                    .port(TestConfigs.SERVER_PORT)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                    .when()
                .post()
                    .then()
                        .statusCode(200)
                            .extract()
                            .body()
                                .as(TokenDTO.class);

        assertNotNull(tokenDTO.getAccessToken());
        assertNotNull(tokenDTO.getRefreshToken());
    }
    @Test
    @Order(2)
    public void testRefresh() {

        var newTokenDTO = given()
                .basePath("/auth/refresh")
                    .port(TestConfigs.SERVER_PORT)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .pathParam("username", tokenDTO.getUsername())
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenDTO.getRefreshToken())
                .when()
                    .put("{username}")
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .as(TokenDTO.class);

        assertNotNull(newTokenDTO.getAccessToken());
        assertNotNull(newTokenDTO.getRefreshToken());
    }
}
