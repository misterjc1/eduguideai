import { Statut } from './Statut';
import { Audit } from './Audit';
export class Profil extends Audit {
  id: number;
  codeProfil: string;
  intitule: string;
  description: string;
  statut: Statut;
  role: string;
}
