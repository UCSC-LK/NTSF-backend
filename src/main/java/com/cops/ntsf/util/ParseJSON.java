package com.cops.ntsf.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParseJSON {
    private static Gson getGsonWithTypeAdapters() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
            @Override
            public JsonElement serialize(LocalDateTime date, Type type, JsonSerializationContext jsonDeserializationContext) throws JsonParseException {
                return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }
        }).create();
    }

    public static String parseToJSONString(Object obj) {
        return ParseJSON.getGsonWithTypeAdapters().toJson(obj);
    }

}
