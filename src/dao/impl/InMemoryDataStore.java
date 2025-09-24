package dao.impl;

import model.Client;
import model.Conseiller;
import model.Contrat;
import model.Sinistre;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryDataStore {
    public static final Map<Long, Client> CLIENTS = new ConcurrentHashMap<>();
    public static final Map<Long, Conseiller> CONSEILLERS = new ConcurrentHashMap<>();
    public static final Map<Long, Contrat> CONTRATS = new ConcurrentHashMap<>();
    public static final Map<Long, Sinistre> SINISTRES = new ConcurrentHashMap<>();

    public InMemoryDataStore() {

    }

    public static List<Client> findClientsByNom(String nom) {
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
        Set<Long> contrats = CONTRATS.values().stream().filter(c-> Objects.equals(c.getClientId(), clientId)).map(Contrat::getId).collect(Collectors.toSet());
        return SINISTRES.values().stream().filter(s -> contrats.contains(s.getContratId())).collect(Collectors.toList());
    }

}