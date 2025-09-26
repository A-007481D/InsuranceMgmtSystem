package dao.impl;

import dao.ConseillerDAO;
import model.Conseiller;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConseillerDAOImpl implements ConseillerDAO {

    private final Connection conn;

    public ConseillerDAOImpl() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    @Override
    public Conseiller save(Conseiller conseiller) {
        try {
            if (conseiller.getId() == null) {
                // INSERT
                String sql = "INSERT INTO conseillers (nom, prenom, email) VALUES (?, ?, ?) RETURNING id";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, conseiller.getNom());
                    ps.setString(2, conseiller.getPrenom());
                    ps.setString(3, conseiller.getEmail());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        conseiller.setId(rs.getLong("id"));
                    }
                }
            } else {
                // UPDATE
                String sql = "UPDATE conseillers SET nom=?, prenom=?, email=? WHERE id=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, conseiller.getNom());
                    ps.setString(2, conseiller.getPrenom());
                    ps.setString(3, conseiller.getEmail());
                    ps.setLong(4, conseiller.getId());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving conseiller", e);
        }
        return conseiller;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM conseillers WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting conseiller", e);
        }
    }

    @Override
    public Optional<Conseiller> findById(Long id) {
        String sql = "SELECT * FROM conseillers WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Conseiller c = new Conseiller(
                        rs.getLong("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                );
                return Optional.of(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding conseiller by id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Conseiller> findAll() {
        List<Conseiller> list = new ArrayList<>();
        String sql = "SELECT * FROM conseillers";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Conseiller c = new Conseiller(
                        rs.getLong("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching conseillers", e);
        }
        return list;
    }
}
