package test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2020/3/20 12:00
 */
public class GsonDoubleInteger {

    public static Gson getGson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(HashMap.class, new JsonDeserializer<HashMap>() {
            @Override
            public HashMap<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
                JsonParseException {
                HashMap<String, Object> resultMap = new HashMap<>();
                JsonObject jsonObject = json.getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                for (Map.Entry<String, JsonElement> entry : entrySet) {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
                return resultMap;
            }

        }).create();
        return gson;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String aaa = "{ a: 1, b: 2.3 }";
        HashMap<String, Object> map = getGson().fromJson(aaa, HashMap.class);
        System.out.println(map);
    }
}
