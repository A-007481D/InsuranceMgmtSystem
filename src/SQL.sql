DROP TABLE IF EXISTS sinistres;
DROP TABLE IF EXISTS contrats;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS conseillers;

CREATE TABLE conseillers (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    conseiller_id INTEGER REFERENCES conseillers(id)
);

CREATE TABLE contrats (
    id SERIAL PRIMARY KEY,
    type_contrat VARCHAR(50) NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    client_id INTEGER REFERENCES clients(id),
    conseiller_id INTEGER REFERENCES conseillers(id)
);

CREATE TABLE sinistres (
    id SERIAL PRIMARY KEY,
    type_sinistre VARCHAR(50) NOT NULL,
    date_declaration DATE NOT NULL,
    description TEXT,
    status VARCHAR(50),
    cout DECIMAL(10,2),
    contrat_id INTEGER REFERENCES contrats(id)
);