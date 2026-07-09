import { Audit } from './Audit';
import { Utilisateur } from './Utilisateur';
export class Session extends Audit {
    id: number;
    codeSession: string;
    startDate: Date;
    endDate: Date;
    codeTerminal: string;
    statut: boolean;
    utilisateur: Utilisateur;
}
