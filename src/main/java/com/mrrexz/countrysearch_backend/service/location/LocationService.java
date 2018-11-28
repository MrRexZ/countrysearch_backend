package com.mrrexz.countrysearch_backend.service.location;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mrrexz.countrysearch_backend.bean.LatLng;
import com.mrrexz.countrysearch_backend.config.Config;
import com.mrrexz.countrysearch_backend.util.location.Haversine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class LocationService implements ILocationService {

    @Resource
    private CacheManager cacheManager;


    @Override
    public LatLng getServerLatLng() throws IOException {
        JsonObject serverLocationJson = getCurrentLocationJSON();
        double latitude = serverLocationJson.get("latitude").getAsDouble();
        double longitude = serverLocationJson.get("longitude").getAsDouble();
        return new LatLng(latitude, longitude);
    }

    @Override
    public LatLng getServerLatLngCache() {
        Cache cache = cacheManager.getCache(Config.LOCATION_CACHE_NAME);
        LatLng serverLatLng = cache.get(Config.SERVER_LAT_LNG_CACHE_KEY, LatLng.class);
        return serverLatLng;
    }

    @Override
    public double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        return Haversine.distance(latitude1, longitude1, latitude2, longitude2);
    }

    private JsonObject getCurrentLocationJSON() throws IOException {
        URL url = new URL(Config.LOCATOR_API);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream stream = connection.getInputStream();
        JsonObject json;

        JsonElement element = new JsonParser().parse(
                new InputStreamReader(stream)
        );
        json = element.getAsJsonObject();
        return json;
    }
}
