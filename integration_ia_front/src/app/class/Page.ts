import { Audit } from './Audit';
export class Page extends Audit {
    id: number;
    codePage: string;
    intitule: string;
    type: number;
    key: number;
}