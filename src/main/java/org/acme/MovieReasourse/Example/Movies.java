package org.acme.MovieReasourse.Example;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.h2.result.Row;

@Data
@Schema(name = "Movie", description = "Movie Representation")
public class Movies {
    private Long id;
    @Schema(required = true )
    private String title;
    private String status;

    public Movies(){}

    public Movies(String title) {
        this.title = title;
    }

    public Movies(Long id, String title) {
        this.title = title;
        this.id = id;
    }

    //method to find all movies in db
    public static Multi<Movies> findAll(PgPool pgPool) {
       return pgPool.query("Select id, title From movies Order By title Desc").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Movies::from);

    }

    private static Movies from(Row row) {
        return new Movies(row.getLong ("id"), row.getString("title"));
    }

}
