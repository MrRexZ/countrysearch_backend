package com.mrrexz.countrysearch_backend.tasks;

import com.mrrexz.countrysearch_backend.bean.LatLng;
import com.mrrexz.countrysearch_backend.service.ILocationService;
import com.mrrexz.countrysearch_backend.util.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class LocationUpdateTask {

    @Resource
    private CacheManager cacheManager;

    @Autowired
    ILocationService iLocationService;

    @Scheduled(fixedRate = 1000)
    public void updateServerLocation() {
        Cache locCache = cacheManager.getCache(Config.LOCATION_CACHE_NAME);
        try {
            LatLng latLng = iLocationService.getServerLatLng();
            locCache.put(Config.SERVER_LAT_LNG_CACHE_KEY, latLng);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}