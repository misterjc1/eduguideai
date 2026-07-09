import { Utilisateur } from "app/user-module/class/Utilisateur";
import { Audit } from "./Audit";
import { StatutLiaison } from "./Enums/StatutLiaison";
import { Inscrit } from "./Inscrit";
import { UtilisateurClass } from "./UtilisateurClass";

export class Liaison extends Audit{
    idLiaison: number;
	codeLiaison: string;
	motif: string;
    statutLiaison: StatutLiaison;
	commentaire: string;
	inscrit: Inscrit;
	utilisateur: UtilisateurClass
}