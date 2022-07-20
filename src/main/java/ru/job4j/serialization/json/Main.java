package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    }
}
