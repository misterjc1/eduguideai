import { Component, OnInit } from '@angular/core';
import { ChatbotService } from 'app/services/chatbot.service';

@Component({
  selector: 'app-assistant-v',
  templateUrl: './assistant-v.component.html',
  styleUrls: ['./assistant-v.component.css']
})
export class AssistantVComponent implements OnInit {

  predefinedQuestions: string[] = [];
  botResponse: string | null = null;  
  errorMessage: string | null = null;  
  userQuestion: string = '';  
  chatHistory: { question: string, response: string }[] = [];  // Historique des conversations
  loading: boolean = false;  // Indicateur de chargement
  userQuestionPlaceholder: string = '';  // Nouvelle propriété


  constructor(private chatbotService: ChatbotService) {}

  ngOnInit(): void {
    this.loadPredefinedQuestions(); 
    this.changePlaceholder();
  }

  loadPredefinedQuestions(): void {
    this.chatbotService.getPredefinedQuestions().subscribe({
      next: (questions) => this.predefinedQuestions = questions,
      error: (err) => console.error('Erreur lors du chargement des questions prédéfinies', err)
    });
  }

  changePlaceholder(): void {
    const placeholders = [
      "Posez une question sur vos cours...",
      "Besoin d'aide pour l'inscription ?",
      "Demandez des conseils pour vos examens"
    ];
    let i = 0;
    setInterval(() => {
      this.userQuestionPlaceholder = placeholders[i];
      i = (i + 1) % placeholders.length;
    }, 3000);  // Change every 3 seconds
  }

  askQuestion(question: string): void {
    this.loading = true;
    this.chatbotService.askQuestion(question).subscribe({
      next: (response) => {
        this.botResponse = response.response;
        this.chatHistory.push({ question, response: this.botResponse });
        this.errorMessage = null;
        this.loading = false;
        setTimeout(() => this.scrollToBottom(), 100);  // Auto-scroll après réponse
      },
      error: (err) => {
        console.error('Erreur lors de la communication avec le bot', err);
        this.errorMessage = 'Erreur lors de la communication avec le bot';
        this.loading = false;
      }
    });
  }
  
  scrollToBottom(): void {
    const chatHistoryElement = document.querySelector('.chat-history');
    if (chatHistoryElement) {
      chatHistoryElement.scrollTop = chatHistoryElement.scrollHeight;
    }
  }
  

  submitQuestion(): void {
    if (this.userQuestion.trim() === '') {
      this.errorMessage = 'Veuillez saisir une question';
      return;
    }

    this.askQuestion(this.userQuestion);  
    this.userQuestion = '';  
  }
}

