package dao.impl;

import model.Client;
import model.Conseiller;
import model.Contrat;
import model.Sinistre;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryDataStore {
    private static final Map<Long, Client> CLIENTS = new ConcurrentHashMap<>();
    private static final Map<Long, Conseiller> CONSEILLERS = new ConcurrentHashMap<>();
    private static final Map<Long, Contrat> CONTRATS = new ConcurrentHashMap<>();
    private static final Map<Long, Sinistre> SINISTRES = new ConcurrentHashMap<>();

    public InMemoryDataStore() {

    }

    public static List<Client> findClientByNom(String nom) {
        return CLIENTS.values().stream()
                .filter(c -> c.getNom().equalsIgnoreCase(nom))
                .sorted(Comparator.comparing(Client::getNom))
                .collect(Collectors.toList());
    }

    public static List<Contrat> findContratByClientId(Long clientId) {
        return CONTRATS.values().stream()
                .filter(ct -> Objects.equals(ct.getClientId(), clientId)).collect(Collectors.toList());
    }

    public static List<Client> findClientsByConseillerId(Long id) {
        return CLIENTS.values().stream().filter(c -> Objects.equals(c.getConseillerId(), id)).collect(Collectors.toList());
    }

    public static List<Sinistre> findSinistresByContratId(Long contratId) {
        return SINISTRES.values().stream().filter(s -> Objects.equals(s.getContratId(), contratId)).collect(Collectors.toList());
    }

    public static List<Sinistre> findSinistresByClientId(Long clientId) {

    }

}