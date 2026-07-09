import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatbotService {

  private readonly API_URL = 'http://localhost:8181/chatbot'; // Base URL de l'API

  constructor(private http: HttpClient) { }

  // Requête pour obtenir les questions prédéfinies
  getPredefinedQuestions(): Observable<string[]> {
    return this.http.get<string[]>(`${this.API_URL}/predefinedQuestions`);
  }

  // Requête pour poser une question à l'IA
  askQuestion(question: string): Observable<any> {
    return this.http.post(`${this.API_URL}/ask`, { question });
  }
}
