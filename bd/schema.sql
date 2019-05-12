DROP TABLE filmscommandes;

drop table platscommandes;

DROP TABLE commande;

DROP TABLE client;

CREATE TABLE client (
    idclient   VARCHAR2(50),
    nom        VARCHAR2(20) NOT NULL,
    prenom     VARCHAR2(20) NOT NULL,
    photo      VARCHAR2(20),
    email      VARCHAR2(100),
    tel        VARCHAR2(10),
    adresse    VARCHAR2(100),
    CONSTRAINT client_c0 PRIMARY KEY ( idclient ),
    CONSTRAINT client_c1 UNIQUE ( nom ,prenom )
);

CREATE TABLE commande (
    idcommande         VARCHAR2(8),
    idclient           VARCHAR2(50) NOT NULL,
    datecommande       DATE NOT NULL,
    prix               NUMBER(4, 2),
    adresselivraison   VARCHAR2(100) NOT NULL,
    CONSTRAINT commande_c0 PRIMARY KEY ( idcommande ),
    CONSTRAINT commande_c1 FOREIGN KEY ( idclient )
        REFERENCES client ( idclient ) ON DELETE CASCADE,
        -- on update cascade
    constraint commande_c2 check ( prix > 0 )
);

CREATE TABLE PlatsCommandes (
    idCommande    VARCHAR2(8),
    idPlat        VARCHAR2(30),
    quantite      INTEGER,
    CONSTRAINT    PlatsCommandes_c0 PRIMARY KEY (idCommande,idPlat),
    CONSTRAINT    PlatsCommandes_c1 FOREIGN KEY (idCommande) 
        REFERENCES commande ( idCommande ) on delete cascade,
        -- on update cascade
    CONSTRAINT    PlatsCommandes_c2 CHECK ( quantite > 0 )
);

CREATE TABLE FilmsCommandes (
    idCommande   VARCHAR2(8),
    idFilm       VARCHAR2(30),
    CONSTRAINT   FilmsCommandes_c0 PRIMARY KEY (idCommande,idFilm),
    CONSTRAINT   FilmsCommandes_c1 FOREIGN KEY (idCommande) 
        REFERENCES commande ( idCommande ) on delete cascade
        -- on update cascade
);

start GestionnaireClient.sql;
start GestionnaireCommande.sql;
start sequences.sql;
start Triggers.sql;
