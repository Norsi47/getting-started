package org.practice.gs;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.practice.gs.Model.TvSeriesResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/pracTvseries")
public class TvSeriesController {

    //all logic has to be done here for some reason

    @RestClient
    TvSeriesClient tvSeriesClient;

    private List<TvSeriesResponse> tvSeriesResponseList = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTvSeries() {
        TvSeriesResponse tvSeriesResponseClassname = tvSeriesClient.get("girls");
        tvSeriesResponseList.add(tvSeriesResponseClassname);
        return Response.ok(tvSeriesResponseList).build();
    }

}
