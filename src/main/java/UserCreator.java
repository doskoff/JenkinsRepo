import com.google.gson.Gson;
import models.UserForId;

import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserCreator {
    public static UserForId userCreator() {
        UserForId user = null;
        BufferedReader reader;
        File file = new File("");
        String a = file.getAbsoluteFile() +"\\src\\main\\resources\\userIdFive.json";
        try {
            reader = new BufferedReader(new FileReader(a));
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            reader.close();

            Gson gson = new Gson();
            user = gson.fromJson(json, UserForId.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }
}
