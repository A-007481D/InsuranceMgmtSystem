-- Drop tables if they exist
DROP TABLE IF EXISTS sinistres;
DROP TABLE IF EXISTS contrats;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS conseillers;

-- Create conseillers table
CREATE TABLE conseillers (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Create clients table
CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    conseiller_id INTEGER REFERENCES conseillers(id)
);

-- Create contrats table
CREATE TABLE contrats (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    client_id INTEGER REFERENCES clients(id),
    conseiller_id INTEGER REFERENCES conseillers(id)
);

-- Create sinistres table
CREATE TABLE sinistres (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    date_declaration DATE NOT NULL,
    description TEXT,
    status VARCHAR(50),
    montant_remboursement DECIMAL(10,2),
    contrat_id INTEGER REFERENCES contrats(id)
);