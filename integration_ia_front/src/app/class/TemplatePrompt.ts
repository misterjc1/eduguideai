import { Audit } from './Audit';
import { Variables } from './Enums/Variables';
export class TemplatePrompt extends Audit {
    idTemplatePrompt: number;
    codeTemplatePrompt: string;
    variables: String;
    listvariables: Variables[];  
    description: string;
    
}