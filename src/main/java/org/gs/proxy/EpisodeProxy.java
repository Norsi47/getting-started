package org.gs.proxy;

import org.gs.model.Episode;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/shows")
@Produces(MediaType.APPLICATION_JSON)
//would look like this as well if it was not in app.prop
//@RegisterRestClient(baseUri = "https://api.tvmaze.com/")
/* Using this site as an example now
 "https://api.tvmaze.com/shows/82"
  82 in this case is the id for game of thrones */
public interface EpisodeProxy {
    @GET
    @Path("{id}/episodes")
    List<Episode> getId(@PathParam("id") Long id);


}
