drop table Film;
drop table Plat;
drop table LigneFacture;
drop table Facture;
drop table Commande;
drop table Client;

create table Client(
    idClient varchar2(50),
    nom varchar2(20) not null,
    prenom varchar2(20) not null,
    photo varchar2(20),
    email varchar2(100),
    tel varchar2(10) ,
    adresse varchar2(120) not null,
    
    constraint Client_C0 primary key (idClient),
    constraint Client_C1 unique (email),
    constraint Client_C2 unique (nom,prenom)
);

create table Commande(
    idCommande varchar2(50),
    idClient varchar2(8) not null,
    idPlats varchar2(4), 
    idFilms varchar2(8),
    dateCommande date not null,
    prix number(4,2),
    adresseLivraison varchar2(120),
    
    constraint Commande_C0 primary key (idCommande),
    constraint Commande_C1 foreign key (idClient) references Client(idClient) on delete cascade -- on update cascade
);

create table Plat(
    idPlats number(8),
    idPlat varchar2(4),
    quantite number(2),
    
    constraint Plat_C0 primary key (idPlats, idPlat),
    constraint Plat_C1 check ( quantite > 0 )
);

create table Film(
    idFilms number(8),
    idFilm varchar2(8),
    
    constraint Film_C0 primary key (idFilms, idFilm)
);

create table Facture(
    idFacture varchar2(8),
    idCommande varchar2(8),
    dateFacture date,
    
    constraint Facture_C0 primary key (idFacture),
    constraint Facture_C1 foreign key (idCommande) references Commande(idCommande) on delete cascade -- on update cascade
);

create table LigneFacture(
    idLigneFacture number(2),
    idFacture varchar2(20),
    Plat varchar2(20) not null,
    quantite number(2) not null,
    prixHT number(2,2),
    
    constraint LigneFacture_C0 primary key (idLigneFacture),
    constraint LigneFacture_C1 foreign key (idFacture) references Facture(idFacture) on delete cascade, --on update cascade
    constraint LigneFacture_C2 check ( prixHT > 0 and prixHT < 49),
    constraint LigneFacture_C3 check ( quantite > 0 )
);
