package org.acme.MovieReasourse.Example;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.aesh.readline.terminal.Key.m;

@Path("/movies")
@Tag(name = "Movie Resource", description = "Movie REST API")
public class MovieResource {

    @Inject
    PgPool pgPoolClient;

    @PostConstruct
    void config() {
        initdb();
    }

    @GET
    public Multi<Movies> get() {
        return Movies.findAll(pgPoolClient);
    }

    private void initdb() {
        //this is to create schema
        pgPoolClient.query("Drop Table if Exists movies").execute()
                .flatMap(m-> pgPoolClient.query("Create Table Movies (id serial primary key, " +
                        "title Text Not null)").execute())
                //inserting data into db
                 .flatMap(m -> pgPoolClient.query("Insert Into Movies (title) Values('The Lord of the Rings')").execute())
                 .flatMap(m -> pgPoolClient.query("Insert Into Movies (title) Values('Harry Potter')").execute())
                .await()
                .indefinitely();
    }


    public static List<Movies> moviesList = new ArrayList<>();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(operationId = "createMovies", summary = "Create a new Movie", description = "Create a new movie to add inside the list")
    @APIResponse(
            responseCode = "201",
            description = "Movie Created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    //method consumes newMovie and produces moviesList
    public Response createMovies(@RequestBody(description = "Movie to create", required = true, content = @Content(schema = @Schema(implementation = Movies.class))
    ) Movies newMovie) {
        moviesList.add(newMovie);
        return Response.status(Response.Status.CREATED).entity(moviesList).build();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getMovies", summary = "getMovies", description = "Get all movies inside the list")
    @APIResponse(
            responseCode = "200",
            description = "Operation Completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    //method to get all movies
    public Response getMovie() {

        //status code is "ok()"
        return Response.ok(moviesList).build();

    }

    //leave this to return media type
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    @Operation(operationId = "countMovies", summary = "Count Movies", description = "Size of the list of movies")
    @APIResponse(
            responseCode = "200",
            description = "Operation Completed",
            content = @Content(mediaType = MediaType.TEXT_PLAIN)
    )
    //return integer, to return size of movie
    public Integer countMovies() {
        return moviesList.size();
    }


    //updates what is already put into api
    @PUT
    @Path("{id}/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(operationId = "updateMovies", summary = "Update existing Movies", description = "Update a movie inside the list")
    @APIResponse(
            responseCode = "200",
            description = "Movie updated",
            content = @Content(mediaType = MediaType.TEXT_PLAIN)
    )
    public Response updateMovie(@Parameter(
            description = "Movie id",
            required = true
    )
                                @PathParam("id") Long id,
                                @Parameter(
                                        description = "Movie title",
                                        required = true
                                ) @PathParam("title") String title) {
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
    @Operation(operationId = "deleteMovies", summary = "Delete an existing Movies", description = "Delete a movie inside the list")
    @APIResponse(
            responseCode = "204",
            description = "Movie deleted",
            content = @Content(mediaType = MediaType.TEXT_PLAIN)
    )
    @APIResponse(
            responseCode = "400",
            description = "Movie not valid",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)

    )
    public Response deleteMovie(
            @PathParam("id") Long id) {
        Movies moviesResponse = new Movies();

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
