package org.acme.MovieReasourse.Example;

import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.Post;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/movies")
public class MovieResource {

    public static List<Movies> moviesList = new ArrayList<>();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //method consumes newMovie and produces moviesList
    public Response createMovies(Movies newMovie) {
        moviesList.add(newMovie);
        return Response.ok(moviesList).build();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //method to get all movies
    public Response getMovie() {

        //status code is "ok()"
        return Response.ok(moviesList).build();

    }

    //leave this to return media type
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    //return integer, to return size of movie
    public Integer countMovies() {
        return moviesList.size();
    }



    //updates what is already put into api
    @PUT
    @Path("{id}/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") Long id,
                               @PathParam("title") String title) {
        moviesList.stream().map(moviesList -> {
            if (moviesList.getId().equals(id)) {
                moviesList.setTitle(title);
            }
            return moviesList;
        }).collect(Collectors.toList());
        return Response.ok(moviesList).build();

    }

    @DELETE()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMovie(
           @PathParam("id") Long id) {
       Optional<Movies> moviesToDelete = moviesList.stream().filter(movies -> movies.getId().equals(id)).findFirst();
        //boolean var is needed to see if it is saved
        boolean removed = false;
        if (moviesToDelete.isPresent()) {
             removed = moviesList.remove(moviesToDelete.get());
        }
        if (removed) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();

    }
//find out how to do the same thing but in a service class and this controller class

}
