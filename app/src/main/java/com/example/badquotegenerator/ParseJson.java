package com.example.badquotegenerator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.gson.JsonPrimitive;

public class ParseJson {
    public static String getQuote(final String json) {
        JsonParser parser = new JsonParser();
        if (json == null) {
            return null;
        }
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonPrimitive quoteText = result.getAsJsonPrimitive("quoteText");
        return quoteText.getAsString();
    }

    public static String getAuthor (final String json) {
        JsonParser parser = new JsonParser();
        if (json == null) {
            return null;
        }
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonPrimitive quoteAuthor = result.getAsJsonPrimitive("quoteAuthor");
        return quoteAuthor.getAsString();
    }
}
