package com.mrrexz.countrysearch_backend.service.location;

import com.mrrexz.countrysearch_backend.bean.LatLng;

import java.io.IOException;

public interface ILocationService {

    LatLng getServerLatLng() throws IOException;

    LatLng getServerLatLngCache();

    double getDistance(double latitude1, double longitude1, double latitude2, double longitude2);
}
