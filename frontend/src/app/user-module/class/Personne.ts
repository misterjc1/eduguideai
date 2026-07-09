import { Audit } from "./Audit";

export class Personne extends Audit {
  id: number;
  codePersonne: string;
  nom: string;
  prenom: string;
  adresse: string;
  email: string;
  telephone: string;
  genre: string;
  fonction: string;
  dateN: Date;
  dateEmbauche: Date;
  identite: string;
	codeStructure : string;
  //ajout
  matricule: string
  
}
