package org.practice.gs.Model;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.practice.gs.TvSeriesClient;

import java.util.ArrayList;
import java.util.List;

public class TvSeriesService {

    @RestClient
    TvSeriesClient tvSeriesClient;

    //need to make it into an arry list to add objects in it
    private List <TvSeriesResponse> tvSeriesResponseList = new ArrayList<>();

    public void getTvSeries() {
        TvSeriesResponse tvSeriesResponseClassName = tvSeriesClient.get("girls");
        //cant use ".add" without array list
        tvSeriesResponseList.add(tvSeriesResponseClassName);

    }





}
