package dao.impl;

import dao.ClientDAO;
import model.Client;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAOImpl implements ClientDAO {
    private final Connection conn;

    public ClientDAOImpl() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    @Override
    public Client save(Client c) {
        try {
            String sql = "INSERT INTO clients (nom, prenom, email, conseiller_id) VALUES (?, ?, ?, ?) RETURNING id";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getNom());
                ps.setString(2, c.getPrenom());
                ps.setString(3, c.getEmail());
                if (c.getConseillerId() != null) ps.setLong(4, c.getConseillerId());
                else ps.setNull(4, Types.BIGINT);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    c.setId(rs.getLong("id"));
                    System.out.println("Successfully inserted new client with ID: " + c.getId());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving client", e);
        }
        return c;
    }

    @Override
    public Optional<Client> findById(Long id) {
        String sql = "SELECT * FROM clients WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM clients WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Client> findByNom(String nom) {
        final String sql = "SELECT id, nom, prenom, email, conseiller_id FROM clients WHERE LOWER(nom) LIKE LOWER(?)";
        List<Client> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nom + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to find clients by nom", ex);
        }
        return list;
    }

    @Override
    public List<Client> findByConseillerId(Long conseillerId) {
        final String sql = "SELECT id, nom, prenom, email, conseiller_id FROM clients WHERE conseiller_id=?";
        List<Client> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, conseillerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to find clients by conseiller id", ex);
        }
        return list;
    }
    private Client mapRow(ResultSet rs) throws SQLException {
        Long conseillerId = rs.getObject("conseiller_id") != null ? rs.getLong("conseiller_id") : null;
        return new Client(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                conseillerId
        );
    }
}
