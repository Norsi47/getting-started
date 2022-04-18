package org.practice.gs;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.practice.gs.Model.TvSeriesResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/singlesearch")
@Produces(MediaType.APPLICATION_JSON)
//added to app.prop
//@RegisterRestClient(baseUri = "https://api.tvmaze.com/")
public interface TvSeriesClient {
    @GET
    @Path("/shows")
    //the "q" is to match the query param
    TvSeriesResponse getTvSeries(@QueryParam("q") String title);



}
