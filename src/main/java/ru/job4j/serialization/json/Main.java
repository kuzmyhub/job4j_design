package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

public class Main {
    public static void main(String[] args) {
        final Site site = new Site(true, 15000,
                new URLAddress("simple-page.com"),
                new String[] {"code", "programs", "chat"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(site));

        final String siteJson = "{"
                + "\"isWorking\":true,"
                + "\"visitors\":15000,"
                + "\"urlAddress\":"
                + "{"
                + "\"address\":\"simple-page.com\""
                + "},"
                + "\"themes\":[\"code\",\"programs\",\"chat\"]"
                + "}";
        final Site siteMod = gson.fromJson(siteJson, Site.class);
        System.out.println(siteMod);

        JSONObject jsonURLAddress = new JSONObject();
        jsonURLAddress.put("address", site.getUrlAddress());

        JSONObject jsonThemes = new JSONObject();
        jsonThemes.put("themes", site.getThemes());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isWorking", site.isWorking());
        jsonObject.put("visitors", site.getVisitors());
        jsonObject.put("URLAddress", jsonURLAddress);
        jsonObject.put("themes", jsonThemes);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(siteJson));
    }
}
