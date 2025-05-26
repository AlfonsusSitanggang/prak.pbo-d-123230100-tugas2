package movieapp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements MovieInterface {
    private Connection conn;

    public MovieDAO() {
        try {
            String url = "jdbc:mysql://localhost:3306/movie_db";
            String user = "root";
            String pass = ""; 
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Movie movie) {
        if (isJudulExists(movie.getJudul())) {
            throw new RuntimeException("Judul film sudah ada!");
        }

        String sql = "INSERT INTO movies (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, movie.getJudul());
            ps.setDouble(2, movie.getAlur());
            ps.setDouble(3, movie.getPenokohan());
            ps.setDouble(4, movie.getAkting());
            ps.setDouble(5, movie.getNilai());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menyimpan data ke database.", e);
        }
    }

    @Override
    public void update(Movie movie, String judulLama) {
        if (!movie.getJudul().equals(judulLama) && isJudulExists(movie.getJudul())) {
            throw new RuntimeException("Judul baru sudah digunakan.");
        }

        String sql = "UPDATE movies SET judul=?, alur=?, penokohan=?, akting=?, nilai=? WHERE judul=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, movie.getJudul());
            ps.setDouble(2, movie.getAlur());
            ps.setDouble(3, movie.getPenokohan());
            ps.setDouble(4, movie.getAkting());
            ps.setDouble(5, movie.getNilai());
            ps.setString(6, judulLama);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal memperbarui data.", e);
        }
    }

    @Override
    public void delete(String judul) {
        String sql = "DELETE FROM movies WHERE judul=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, judul);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghapus data.", e);
        }
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT * FROM movies";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getString("judul"),
                        rs.getDouble("alur"),
                        rs.getDouble("penokohan"),
                        rs.getDouble("akting")
                );
                list.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data dari database.", e);
        }
        return list;
    }

    @Override
    public boolean isJudulExists(String judul) {
        String sql = "SELECT COUNT(*) FROM movies WHERE judul=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, judul);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}