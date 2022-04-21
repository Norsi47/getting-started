package org.acme.MovieReasourse.Example;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Schema(name = "Movie", description = "Movie Representation")
public class Movies {
    private Long id;
    private String title;


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
      return pgPool.query("SELECT id, title FROM movies ORDER BY title DESC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Movies::from);

    }

    private static Movies from (Row row) {
        return new Movies(row.getLong("id"), row.getString("title"));
    }

}
