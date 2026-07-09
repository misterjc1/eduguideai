// Interface CareerRecommendation
export interface CareerRecommendation {
  nameMetier: string;
  niveauAcces: string;
  descriptionMetier: string;
  statistique: {
    offreEmploi: number;
    salaireMoyen: string;
  };
  defis: string;
}
