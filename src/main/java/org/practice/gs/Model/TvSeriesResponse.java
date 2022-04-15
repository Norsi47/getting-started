package org.practice.gs.Model;

import lombok.Data;

import java.net.URL;

@Data
public class TvSeriesResponse {

    private Long id;
    private String name;
    private URL url;
    private String language;
    private String premiered;
    private String ended;
    private URL officialSite;
}
