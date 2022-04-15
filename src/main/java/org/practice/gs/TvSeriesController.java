package org.practice.gs;

import org.practice.gs.Model.TvSeriesService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pracTvseries")
public class TvSeriesController {

    @Inject
    TvSeriesService tvSeriesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTvSeries() {
        tvSeriesService.getTvSeries();
        return Response.ok(tvSeriesService).build();
    }

}
