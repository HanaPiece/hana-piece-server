package com.project.hana_piece.common.util;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.project.hana_piece.ai.constant.GeminiResponseField;
import com.project.hana_piece.common.exception.JsonElementNotFoundException;
import com.project.hana_piece.common.exception.ValueInvalidException;
import java.util.Map;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JsonUtil {

    private final Gson gson = new Gson();

    public <T> T extractProperty(JsonObject jsonObject, GeminiResponseField key,
        Class<T> clazz) throws RuntimeException {
        JsonElement jsonElement = findProperty(jsonObject, key.getProperty());

        if (jsonElement == null) {
            throw new JsonElementNotFoundException();
        }
        return gson.fromJson(jsonElement, clazz);
    }

    public <T> T fromJson(JsonElement jsonElement, Class<T> jsonObjectClass) {
        return gson.fromJson(jsonElement, jsonObjectClass);
    }

    public JsonObject toJson(String payload) {
        return gson.fromJson(payload, JsonObject.class);
    }

    /**
     * 재귀적 탐색을 통해 JsonElement 요소 중 특정 key 값을 갖는 JsonElement 탐색
     * @param jsonElement
     * @param targetKey
     * @return
     */
    public JsonElement findProperty(JsonElement jsonElement, String targetKey) {
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                if (entry.getKey().equals(targetKey)) {
                    return entry.getValue();
                }
                JsonElement foundElement = findProperty(entry.getValue(), targetKey);
                if (foundElement != null) {
                    return foundElement;
                }
            }
        } else if (jsonElement.isJsonArray()) {
            for (JsonElement element : jsonElement.getAsJsonArray()) {
                JsonElement foundElement = findProperty(element, targetKey);
                if (foundElement != null) {
                    return foundElement;
                }
            }
        }
        return null;
    }
}

