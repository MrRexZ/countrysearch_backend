package com.mrrexz.countrysearch_backend.service;

import com.mrrexz.countrysearch_backend.bean.LatLng;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ILocationService {

    LatLng getServerLatLng() throws IOException;
}
