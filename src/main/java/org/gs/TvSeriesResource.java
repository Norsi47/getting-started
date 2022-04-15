package org.gs;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.gs.model.Episode;
import org.gs.model.TvSeries;
import org.gs.proxy.EpisodeProxy;
import org.gs.proxy.TvSeriesProxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/tvseries")
public class TvSeriesResource {

    @RestClient
    TvSeriesProxy proxy;

    @RestClient
    EpisodeProxy episodeProxy;

    private List<TvSeries> tvSeriesList = new ArrayList();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("title") String title) {
        //making the interface class and response class, gives us
        //abilty to pick what ever we want in it
        TvSeries tvSeriesClassName = proxy.get(title);
        List<Episode> episodesList = episodeProxy.getId(tvSeriesClassName.getId());
        return Response.ok(episodesList).build();
    }

    //my practice
    @GET
    @Path("/Lookup")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLookup(@QueryParam("title") String title) {
        TvSeries tvSeries = proxy.get(title);
        //doing this will continue to add the shows into a list
        tvSeriesList.add(tvSeries);
        return Response.ok(tvSeriesList).build();
    }

}
