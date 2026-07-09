import { Audit } from './Audit';
import { TypeService } from './Enums/TypeService';
import { Service } from './Service';
import { TypeCinematique } from './TypeCinematique';
export class Cinematique extends Audit {
    forEach(arg0: (element: any) => void) {
      throw new Error('Method not implemented.');
    }
    idCinematique: number;
    codeCinematique: string;
    intitule: string;
    //parDefaut: Boolean;
    image:string;
    typeCinematique: TypeCinematique;  // Ajout d'une propriété pour le lien avec TypeCinematique
    typeService: Service; 

}