import { Audit } from "./Audit";
import { TypeUtilisateur } from "./Enums/TypeUtilisateur";
import { Inscrit } from "./Inscrit";
import { Liaison } from "./Liaison";

export class UtilisateurClass extends Audit {
	idUtilisateur: number;
    codeUtilisateur: string;
    username: string;
    password: string;
    matricule: String;
	nom: String;
	prenom: String;
	telephone: String;
	email: String;
	type: TypeUtilisateur;
	liaison: Liaison;
	inscrit: Inscrit;
	pin: String;
}
