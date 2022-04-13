package org.gs.model;

import lombok.Data;

import java.net.URL;
import java.util.Set;

@Data
public class TvSeries {

    private Long id;
    private String name;
    private URL url;
    private String summary;
    private String language;
    private Set<String> genres;
    private URL officialSite;

}
