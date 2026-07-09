import { Audit } from './Audit';
import { TypeService } from './Enums/TypeService';

export class Service extends Audit {
    idService?: number;
    codeService?: string;
    typeService?: TypeService;
    logo?: string;
    message?: string;
    logoUrl?: string;
}