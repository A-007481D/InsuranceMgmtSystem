package dao.impl;

import dao.ContratDAO;
import model.Contrat;
import enums.TypeContrat;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContratDAOImpl implements ContratDAO {
    private final Connection conn = DBConnection.getInstance().getConnection();

    @Override
    public Contrat save(Contrat c) {
        if (c == null) throw new IllegalArgumentException("Contrat is null");
        if (c.getId() == null) {
            final String sql = "INSERT INTO contrats (type_contrat, date_debut, date_fin, client_id) VALUES (?, ?, ?, ?) RETURNING id";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getTypeContrat().name());
                ps.setDate(2, Date.valueOf(c.getDateDebut()));
                ps.setDate(3, Date.valueOf(c.getDateFin()));
                ps.setLong(4, c.getClientId());
                System.out.println("[ContratDAOImpl] Executing INSERT");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        long id = rs.getLong("id");
                        c.setId(id);
                        System.out.println("[ContratDAOImpl] Inserted id=" + id);
                    } else {
                        throw new RuntimeException("Insert returned no id");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Failed to insert contrat", ex);
            }
        } else {
            final String sql = "UPDATE contrats SET type_contrat=?, date_debut=?, date_fin=?, client_id=? WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getTypeContrat().name());
                ps.setDate(2, Date.valueOf(c.getDateDebut()));
                ps.setDate(3, Date.valueOf(c.getDateFin()));
                ps.setLong(4, c.getClientId());
                ps.setLong(5, c.getId());
                System.out.println("[ContratDAOImpl] Executing UPDATE id=" + c.getId());
                int updated = ps.executeUpdate();
                System.out.println("[ContratDAOImpl] Updated rows=" + updated);
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Failed to update contrat", ex);
            }
        }
        return c;
    }

    @Override
    public Optional<Contrat> findById(Long id) {
        final String sql = "SELECT id, type_contrat, date_debut, date_fin, client_id FROM contrats WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to find contrat by id", ex);
        }
        return Optional.empty();
    }

    @Override
    public List<Contrat> findAll() {
        final String sql = "SELECT id, type_contrat, date_debut, date_fin, client_id FROM contrats";
        List<Contrat> list = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to list contrats", ex);
        }
        return list;
    }

    @Override
    public boolean deleteById(Long id) {
        final String sql = "DELETE FROM contrats WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int affected = ps.executeUpdate();
            System.out.println("[ContratDAOImpl] Deleted rows=" + affected + " for id=" + id);
            return affected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to delete contrat", ex);
        }
    }

    @Override
    public List<Contrat> findByClientId(Long clientId) {
        final String sql = "SELECT id, type_contrat, date_debut, date_fin, client_id FROM contrats WHERE client_id=?";
        List<Contrat> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to find contrats by client id", ex);
        }
        return list;
    }

    private Contrat mapRow(ResultSet rs) throws SQLException {
        return new Contrat(
                rs.getLong("id"),
                TypeContrat.valueOf(rs.getString("type_contrat")),
                rs.getDate("date_debut").toLocalDate(),
                rs.getDate("date_fin").toLocalDate(),
                rs.getLong("client_id")
        );
    }
}
