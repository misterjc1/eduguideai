import { Audit } from "./Audit";
import { Niveau } from "./Niveau";

export class Inscrit extends Audit{
    idInscrit: number;
	codeInscrit: string;
	matricule: string;
    nom: string;
	prenom: string;
	email: string;
	telephone: string;
	niveau: Niveau
}