export interface DataMenu {
    id?: string;
    image?: string;
    type?: string;
    prix?: number;
    ingredients?: string[];
}

export interface ListePlats {
    plats: DataMenu[];
}