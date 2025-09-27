package dao.impl;

import dao.ClientDAO;
import model.Client;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAOImpl implements ClientDAO {
    private final Connection conn = DBConnection.getInstance().getConnection();

    @Override
public Client save(Client c) {
        if (c.getId() == null) {
            final String sql = "INSERT INTO clients (nom, prenom, email, conseiller_id) VALUES (?, ?, ?, ?) RETURNING id";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getNom());
                ps.setString(2, c.getPrenom());
                ps.setString(3, c.getEmail());
                if (c.getConseillerId() != null) ps.setLong(4, c.getConseillerId());
else ps.setNull(4, Types.INTEGER);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) c.setId(rs.getLong("id"));
}
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
}
        } else {
            final String sql = "UPDATE clients SET nom=?, prenom=?, email=?, conseiller_id=? WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getNom());
                ps.setString(2, c.getPrenom());
                ps.setString(3, c.getEmail());
                if (c.getConseillerId() != null) ps.setLong(4, c.getConseillerId());
else ps.setNull(4, Types.INTEGER);
                ps.setLong(5, c.getId());
                ps.executeUpdate();
} catch (SQLException ex) {
                throw new RuntimeException(ex);
}
        }
        return c;
}

    @Override
    public Optional<Client> findById(Long id) {
        final String sql = "SELECT id, nom, prenom, email, conseiller_id FROM clients WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
}
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
}
        return Optional.empty();
}

    @Override
    public List<Client> findAll() {
        final String sql = "SELECT id, nom, prenom, email, conseiller_id FROM clients";
        List<Client> list = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
} catch (SQLException ex) {
            throw new RuntimeException(ex);
}
        return list;
}

    @Override
    public boolean deleteById(Long id) {
        final String sql = "DELETE FROM clients WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
return ps.executeUpdate() > 0;
} catch (SQLException ex) {
            throw new RuntimeException(ex);
}
    }

    // Keep these two, optional: they provide efficient DB filtering if needed.
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
            throw new RuntimeException(ex);
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
            throw new RuntimeException(ex);
}
        return list;
}

    private Client mapRow(ResultSet rs) throws SQLException {
        return new Client(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                (Long) rs.getObject("conseiller_id")
        );
}
}
