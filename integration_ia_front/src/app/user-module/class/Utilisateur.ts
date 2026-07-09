// Note.ts
export interface Note {
    semestre: string;
    module: string;
    libelle: string;
    valeur: string;
}


import { Audit } from './Audit';
import { Personne } from './Personne';
import { Profil } from './Profil';
import { Statut } from './Statut';
export class Utilisateur extends Audit {
    id: number;
    codeUtilisateur: string;
    username: string;
    password: string;
    email: string;
    phone: string;
    type: string;       // AGENT, INSCRIT, TUTEUR
    statut: Statut;
    personne: Personne;
    profil: Profil;
    referenceExterne: string;

    notes?: any[];
}
