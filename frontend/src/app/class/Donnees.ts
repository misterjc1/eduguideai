import { Audit } from './Audit';
import { Parametre } from './Parametre';
export class Donnees extends Audit {
    idDonnee: number;
    codeDonnee: String;
    codeParametre: Parametre;
    intituleD: String;
    valeurD: String;
    fichierJoint: String;    
   
}