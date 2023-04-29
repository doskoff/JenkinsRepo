import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Post;
import models.UserForId;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import utils.JsonReader;
import utils.Utils;

import java.util.List;


import static io.restassured.RestAssured.given;


public class MainTest {
    Response response;
    private static final Post expectedPost = new Post();
    UserForId expectedUserForIdFive = new UserForId();
    Logger logger = AqualityServices.getLogger();

    @Test
    public void testCases() {
        //System.setProperty("log4j.properties", "src/main/resources/log4j.properties");
        //First case
        logger.info("FIRST STEP");
        RestAssured.baseURI = JsonReader.getUrl();
        logger.info("Get url");
        response = RestAssured.get(JsonReader.getFirstRequest());
        logger.info("Get response");
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(JsonReader.getCode()), "Status code is not 200");
        logger.info("Status code is 200");
        Assert.assertTrue(testMetods.isThatJson(response), "The list in response body is not json");
        logger.info("The list in response body is not json");
        Assert.assertTrue(testMetods.idChange(response), "Posts are not sorted ascending");
        logger.info("Posts are sorted ascending");
        //Second case
        logger.info("SECOND STEP");
        response = testMetods.getResponse(JsonReader.getSecondRequest());
        logger.info("Get response");
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(JsonReader.getCode()), "Status code is not 200");
        logger.info("Status code is 200");
        Post postSecond = testMetods.deserialization(response);
        logger.info("Deserialization");
        Assert.assertTrue(testMetods.userCompare(postSecond), "Post information is not correct");
        logger.info("Post information is correct");
        //Third case
        logger.info("THIRD STEP");
        response = testMetods.getResponse(JsonReader.getThirdRequest());
        logger.info("Get response");
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(JsonReader.getSecondCode()), "Status code is not 404");
        logger.info("Status code is 404");
        Assert.assertEquals(response.getBody().asString(), "{}", "Response body is not empty");
        logger.info("Response body is empty");
        //Fourth case
        logger.info("FOURTH STEP");
        expectedPost.setBody(Utils.getRandom());
        logger.info("Set body");
        expectedPost.setTitle(Utils.getRandom());
        logger.info("Set title");
        expectedPost.setUserId(Integer.parseInt(JsonReader.getUserId()));
        logger.info("Set user id");
        response = given()
                .contentType(ContentType.JSON)
                .body(expectedPost)
                .when()
                .post(JsonReader.getFirstRequest());
        logger.info("Get response");
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(JsonReader.getThirdCode()), "Status code is not 201");
        logger.info("Status code is 201");
        Post post = testMetods.deserialization(response);
        logger.info("Deserialization");
        Assert.assertTrue(testMetods.postCompare(expectedPost, post), "Post information is not correct");
        logger.info("Post information is correct");
        //Fifth case
        logger.info("Fifth step");
        response = RestAssured.get(JsonReader.getFifthRequest());
        logger.info("Get response");
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(JsonReader.getCode()), "Status code is not 200");
        logger.info("Status code is not 200");
        Assert.assertTrue(testMetods.isThatJson(response),"The list in response body is not json");
        logger.info("The list in response body is json");
        expectedUserForIdFive = UserCreator.userCreator();
        logger.info("Expected user is created");
        List<UserForId> userList = RequestSteps.getResource(response, UserForId.class, true);
        UserForId actual = RequestSteps.makeUserForId(userList);
        logger.info("Actual user is created");
        Assert.assertEquals(expectedUserForIdFive, actual,"Data is not equals");
        logger.info("Data is equals");
        //Six case
        logger.info("SIX STEP");
        response = testMetods.getResponse(JsonReader.getSixRequest());
        logger.info("Get response");
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(JsonReader.getCode()),"Status code is not 200");
        logger.info("Status code is 200");
        List<UserForId> userList1 = RequestSteps.getResource(response, UserForId.class, false);
        UserForId actual1 = RequestSteps.makeUserForId(userList1);
        logger.info("Actual user is created");
        Assert.assertEquals(expectedUserForIdFive, actual1,"User data not matches with user data in the previous step.");
        logger.info("User data matches with user data in the previous step.");

    }
}
