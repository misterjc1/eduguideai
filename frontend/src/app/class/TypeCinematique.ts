import { Audit } from './Audit';
import { Niveau } from './Enums/Niveau';
export class TypeCinematique extends Audit {
    idTypeCinematique: number;
    codeTypeCinematique: string;
    niveau: Niveau;
    typeBouton: string;
    choixMultiple: string;
    
}