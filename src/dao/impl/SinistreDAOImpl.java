package dao.impl;

import dao.SinistreDAO;
import model.Sinistre;
import enums.TypeSinistre;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SinistreDAOImpl implements SinistreDAO {
    private final Connection conn = DBConnection.getInstance().getConnection();

    @Override
    public Sinistre save(Sinistre s) {
        if (s == null) throw new IllegalArgumentException("Sinistre is null");
        if (s.getId() == null) {
            String sql = "INSERT INTO sinistres (type_sinistre, date_time, cout, description, contrat_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, s.getTypeSinistre().name());
                ps.setTimestamp(2, Timestamp.valueOf(s.getDateTime()));
                ps.setBigDecimal(3, java.math.BigDecimal.valueOf(s.getCout()));
                ps.setString(4, s.getDescription());
                ps.setLong(5, s.getContratId());
                System.out.println("[SinistreDAOImpl] Executing INSERT");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        long id = rs.getLong("id");
                        s.setId(id);
                        System.out.println("[SinistreDAOImpl] Inserted id=" + id);
                    } else {
                        throw new RuntimeException("Insert returned no id");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to insert sinistre", e);
            }
        } else {
            String sql = "UPDATE sinistres SET type_sinistre=?, date_time=?, cout=?, description=?, contrat_id=? WHERE id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, s.getTypeSinistre().name());
                ps.setTimestamp(2, Timestamp.valueOf(s.getDateTime()));
                ps.setBigDecimal(3, java.math.BigDecimal.valueOf(s.getCout()));
                ps.setString(4, s.getDescription());
                ps.setLong(5, s.getContratId());
                ps.setLong(6, s.getId());
                System.out.println("[SinistreDAOImpl] Executing UPDATE id=" + s.getId());
                int updated = ps.executeUpdate();
                System.out.println("[SinistreDAOImpl] Updated rows=" + updated);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to update sinistre", e);
            }
        }
        return s;
    }

    @Override
    public Optional<Sinistre> findById(Long id) {
        String sql = "SELECT * FROM sinistres WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find sinistre by id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Sinistre> findAll() {
        String sql = "SELECT * FROM sinistres";
        List<Sinistre> list = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to list sinistres", e);
        }
        return list;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM sinistres WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int affected = ps.executeUpdate();
            System.out.println("[SinistreDAOImpl] Deleted rows=" + affected + " for id=" + id);
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete sinistre", e);
        }
    }

    @Override
    public List<Sinistre> findByContratId(Long contratId) {
        String sql = "SELECT * FROM sinistres WHERE contrat_id=?";
        List<Sinistre> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, contratId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find sinistres by contrat id", e);
        }
        return list;
    }

    @Override
    public List<Sinistre> findByClientId(Long clientId) {
        String sql = "SELECT s.* FROM sinistres s JOIN contrats c ON s.contrat_id = c.id WHERE c.client_id = ?";
        List<Sinistre> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find sinistres by client id", e);
        }
        return list;
    }

    @Override
    public List<Sinistre> findBefore(java.time.LocalDateTime dateTime) {
        String sql = "SELECT * FROM sinistres WHERE date_time < ?";
        List<Sinistre> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(dateTime));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find sinistres before date", e);
        }
        return list;
    }

    @Override
    public List<Sinistre> findByCoutGreaterThan(Double montant) {
        String sql = "SELECT * FROM sinistres WHERE cout > ?";
        List<Sinistre> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, java.math.BigDecimal.valueOf(montant));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find sinistres by cout > montant", e);
        }
        return list;
    }

    private Sinistre mapRow(ResultSet rs) throws SQLException {
        return new Sinistre(
                rs.getLong("id"),
                TypeSinistre.valueOf(rs.getString("type_sinistre")),
                rs.getTimestamp("date_time").toLocalDateTime(),
                rs.getBigDecimal("cout").doubleValue(),
                rs.getString("description"),
                rs.getLong("contrat_id")
        );
    }
}
