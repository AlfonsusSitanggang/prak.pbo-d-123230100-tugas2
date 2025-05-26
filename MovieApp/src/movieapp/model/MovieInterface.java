package movieapp.model;

import java.util.List;

public interface MovieInterface {
    void insert(Movie movie);
    void update(Movie movie, String judulLama);
    void delete(String judul);
    List<Movie> getAll();
    boolean isJudulExists(String judul);
}