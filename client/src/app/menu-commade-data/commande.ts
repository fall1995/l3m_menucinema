export interface CommandeData {
    idCommade?: number;
    idClient?: any[];
    idPlat?: any[];
    date?: any;
    prix?: number;
    adresseLivraison?: string;
}
export interface Commande {
    idClient?: string;
    idPlats?: any[];
    idFilms?: any[];
    adresseLivraison?: string;
}