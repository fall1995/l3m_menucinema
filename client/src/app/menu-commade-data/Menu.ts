
export interface ListePlats {
    
    plats?: plats[];
}

export interface plats{
    id?: string;
    image?: string;
    type?: string;
    prix?: number;
    ingredients?: string[];
}