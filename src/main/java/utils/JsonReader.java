package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonReader {
    private static final String jsonFileName = "Info.json";

    private static final ISettingsFile jsonSettingsFile = new JsonSettingsFile(jsonFileName);
    public static String getUrl() {
        return jsonSettingsFile.getValue("/url").toString();

    }
    public static String getCode() {
        return jsonSettingsFile.getValue("/firstCode").toString();
    }
    public static String getSecondCode() {
        return jsonSettingsFile.getValue("/secondCode").toString();
    }
    public static String getThirdCode() {
        return jsonSettingsFile.getValue("/thirdCode").toString();
    }
    public static String getSecondRequest() {
        return jsonSettingsFile.getValue("/secondRequest").toString();
    }
    public static String getFourthRequest() {
        return jsonSettingsFile.getValue("/fourthRequest").toString();
    }
    public static String getThirdRequest() {
        return jsonSettingsFile.getValue("/thirdRequest").toString();
    }
    public static String getFirstRequest(){
        return jsonSettingsFile.getValue("/firstRequest").toString();
    }
    public static String getUserId(){
        return jsonSettingsFile.getValue("/userId").toString();
    }
    public static String getFifthRequest(){
        return jsonSettingsFile.getValue("/fifthRequest").toString();
    }
    public static String getUserIdSecond(){
        return jsonSettingsFile.getValue("/userIdSecond").toString();
    }
    public static String getSixRequest(){
        return jsonSettingsFile.getValue("/sixRequest").toString();
    }
    public static String userIdFirst(){
        return jsonSettingsFile.getValue("/userIdFirst").toString();
    }
    public static String id(){
        return jsonSettingsFile.getValue("/id").toString();
    }
}
