package org.gs.model;

import lombok.Data;

@Data
public class Episode {

    //in this case only using name summary and season
    private Long id;
    private String name;
    private Long season;
    private String summary;
}
