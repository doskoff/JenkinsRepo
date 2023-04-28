import com.google.gson.Gson;
import io.restassured.response.Response;
import models.Post;
import models.UserForId;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.JsonReader;

import static io.restassured.RestAssured.given;
public class testMetods {
    static Gson gson = new Gson();
    public static boolean idChange(Response response){
        boolean exept=true;

        JSONArray jsonArray = new JSONArray(response.getBody().asString());
        for (int i = 0; i < jsonArray.length() - 1; i++) {
            JSONObject obj1 = jsonArray.getJSONObject(i);
            JSONObject obj2 = jsonArray.getJSONObject(i + 1);
            int id1 = obj1.getInt("id");
            int id2 = obj2.getInt("id");
            if (id1>id2) {
                exept = false;
                break;
            }
        }
        return exept;
    }
    public static boolean isThatJson(Response response){
        boolean isItJson;
        JSONArray jsonArray = new JSONArray(response.getBody().asString());
        if (jsonArray!=null){
            isItJson=true;
        }else {
            isItJson=false;
        }
        return isItJson;
    }

    public static Boolean userCompare(Post user){
        return Integer.parseInt(JsonReader.userIdFirst()) ==user.getUserId()
                && Integer.parseInt(JsonReader.id())== user.getId()
                && !user.getTitle().isEmpty()
                && !user.getBody().isEmpty();
    }
    public static boolean postCompare(Post expected, Post actual){
        return  (expected.getTitle().equals(actual.getTitle())&&
        expected.getBody().equals(actual.getBody())&&
                expected.getUserId()==actual.getUserId())&&
                Integer.valueOf(expected.getId()) != null;

    }
    public static Response getResponse(String request){
        return given()
                .when()
                .get(request);
    }
    public static Post deserialization(Response response){
        String responseBody = response.getBody().asString();
        return gson.fromJson(responseBody, Post.class);
    }
}
