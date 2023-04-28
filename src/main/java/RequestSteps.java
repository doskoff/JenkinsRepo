import io.restassured.response.Response;
import models.UserForId;
import org.apache.hc.core5.http.HttpStatus;
import utils.JsonReader;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.MoreCollectors.onlyElement;

public class RequestSteps {
    public static <T> List<T> getResource(Response response, Class<T> clazz, boolean isList) {
        if (isList) {
            return response
                    .then().statusCode(HttpStatus.SC_OK)
                    .extract().body().jsonPath().getList("", clazz);
        } else {
            T object = response
                    .then().statusCode(HttpStatus.SC_OK)
                    .extract().body().as(clazz);
            return Collections.singletonList(object);
        }
    }
    public static UserForId makeUserForId(List<UserForId> usersForId){
        return usersForId.stream().filter(x -> x.getId() == Integer.parseInt(JsonReader.getUserIdSecond())).collect(onlyElement());
    }
}