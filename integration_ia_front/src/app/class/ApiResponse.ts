import { Audit } from './Audit';
import { Cinematique } from './Cinematique';

interface ApiResponse {
  payload: Cinematique[];  // La liste des cinématiques
  message: string;         // Message de réponse
  status: string;          // Statut de la réponse
}
