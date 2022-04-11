package practice.onye;

import lombok.Data;
import org.atmosphere.config.service.Post;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/pracMovies")
public class PracMovieController {

    public static List<PracMovieRequest> pracMovieRequests = new ArrayList<>();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makingMovies(PracMovieRequest pracMovieRequest) {
        pracMovieRequests.add(pracMovieRequest);
        return Response.ok(pracMovieRequest).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMakingMovies() {
        return Response.ok(pracMovieRequests).build();
    }


}
