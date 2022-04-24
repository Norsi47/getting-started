package org.acme.MovieReasourse.Example;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;
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

    //find movie by id, used Uni to find sepcific movie

    public static Uni<Movies> findByID (PgPool client, Long id) {
        return client
                .preparedQuery("SELECT id, title FROM movies WHERE id = $1")
                .execute(Tuple.of(id))
                .onItem()
                .transform(m -> m.iterator().hasNext() ? from(m.iterator().next()) : null);

    }


    public static Uni<Long> saveDB(PgPool client, String title) {
        return client
                .preparedQuery("INSERT INT MOVIES (title) VALUE ($1) RETURNING id")
                .execute(Tuple.of(title))
                .onItem()
                .transform(m -> m.iterator().next().getLong("id"));
    }

    //method to find all movies in db
    public static Multi<Movies> findAll(PgPool pgPool) {
      return pgPool.query("SELECT id, title FROM movies ORDER BY title DESC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem()
              .transform(Movies::from);

    }

    public static  Uni <Boolean> deleteDB(PgPool client, Long id) {
        return client
                .preparedQuery("DELETE FROM movies WHERE id = $1")
                .execute(Tuple.of(id))
                .onItem()
                .transform(m -> m.rowCount() == 1);

    }

    private static Movies from (Row row) {
        return new Movies(row.getLong("id"), row.getString("title"));
    }

}
