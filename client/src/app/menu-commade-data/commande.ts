export interface CommandeData {
    idCommade?: number;
    idClient?: string;
    idPlat?: string;
    date?: any;
    prix?: number;
    adresse?: string;
}
export interface Commande {
    idClient?: string;
    idPlats?: any[];
    idFilms?: any[];
    adresseLivraison?: string;
}