import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SetupSchool {
    public static void setup() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    }
}
