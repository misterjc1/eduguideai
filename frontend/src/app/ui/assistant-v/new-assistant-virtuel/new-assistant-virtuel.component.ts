import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit, Optional, AfterViewChecked, ElementRef, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { CHATBOT_ASK, CHATBOT_QUESTIONS } from 'app/helpers/url.constants';

const SESSION_KEY = 'av_chat_messages';

interface ChatMessage {
  role: 'user' | 'ai';
  content: string;
  time: string;
  loading?: boolean;
}

@Component({
  selector: 'app-new-assistant-virtuel',
  templateUrl: './new-assistant-virtuel.component.html',
  styleUrls: ['./new-assistant-virtuel.component.css']
})
export class NewAssistantVirtuelComponent implements OnInit, AfterViewChecked {
  @ViewChild('chatBottom') private chatBottom!: ElementRef;

  messages: ChatMessage[] = [];
  question = '';
  predefinedQuestions: string[] = [];
  isLoading = false;
  userName = '';
  userInitial = 'E';

  // Ne scroller vers le bas que quand un nouveau message arrive
  private shouldScroll = false;

  constructor(
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private http: HttpClient,
    private tokenStorage: TokenStorageService,
  ) {}

  ngOnInit(): void {
    const user = this.tokenStorage.getUser();
    this.userName = user?.prenom || user?.username || 'Étudiant';
    this.userInitial = this.userName[0]?.toUpperCase() || 'E';
    this.loadPredefinedQuestions();

    // Restaurer la session depuis sessionStorage
    const saved = sessionStorage.getItem(SESSION_KEY);
    if (saved) {
      try {
        const parsed: ChatMessage[] = JSON.parse(saved);
        this.messages = parsed.filter(m => !m.loading);
        if (this.messages.length > 0) {
          this.shouldScroll = true;
          return;
        }
      } catch { /* ignore, on repart de zéro */ }
    }
    this.initWelcome();
  }

  ngAfterViewChecked(): void {
    if (this.shouldScroll) {
      this.shouldScroll = false;
      this.scrollToBottom();
    }
  }

  private initWelcome(): void {
    this.pushAiMessage(
      `Bonjour **${this.userName}** ! 👋\n\nJe suis votre assistant académique **EduGuideAI**, alimenté par l'IA Gemini. ` +
      `Je suis là pour vous aider avec vos études, votre orientation et votre réussite académique.\n\n` +
      `Posez-moi n'importe quelle question ou choisissez un sujet à gauche.`
    );
  }

  loadPredefinedQuestions(): void {
    this.http.get<string[]>(CHATBOT_QUESTIONS).subscribe({
      next: (data) => { this.predefinedQuestions = data; },
      error: () => {
        this.predefinedQuestions = [
          'Comment améliorer mes résultats scolaires ?',
          'Quels métiers correspondent à mon domaine ?',
          'Comment mieux préparer mes examens ?',
          'Comment gérer efficacement mon temps d\'étude ?',
          'Quels conseils pour réussir un entretien ?',
          'Comment trouver un stage pertinent ?',
          'Comment financer mes études au Burkina Faso ?',
          'Quels outils pour la recherche académique ?',
          'Quelles méthodes d\'étude sont les plus efficaces ?',
          'Comment gérer mon temps d\'étude ?',
          'Quels sont les outils disponibles pour la recherche académique ?',
          'Comment développer de bonnes habitudes d\'étude ?',
          'Quels conseils pour réussir un entretien d\'embauche ?',
          'Comment choisir ma filière d\'études ?',
          'Quels sont les critères d\'une bonne lettre de motivation ?',
        ];
      }
    });
  }

  sendQuestion(q?: string): void {
    const text = (q || this.question).trim();
    if (!text || this.isLoading) return;

    this.messages.push({ role: 'user', content: text, time: this.now() });
    this.question = '';
    this.isLoading = true;
    this.shouldScroll = true;

    const loading: ChatMessage = { role: 'ai', content: '', time: this.now(), loading: true };
    this.messages.push(loading);

    this.http.post<{ response: string }>(CHATBOT_ASK, { question: text }).subscribe({
      next: (res) => {
        const idx = this.messages.indexOf(loading);
        if (idx >= 0) this.messages[idx] = { role: 'ai', content: res.response, time: this.now() };
        this.isLoading = false;
        this.shouldScroll = true;
        this.saveSession();
      },
      error: () => {
        const idx = this.messages.indexOf(loading);
        if (idx >= 0) this.messages[idx] = {
          role: 'ai',
          content: 'Désolé, une erreur est survenue. Vérifiez votre connexion et réessayez.',
          time: this.now()
        };
        this.isLoading = false;
        this.shouldScroll = true;
        this.saveSession();
      }
    });
  }

  onKeyDown(e: KeyboardEvent): void {
    if (e.key === 'Enter' && !e.shiftKey) { e.preventDefault(); this.sendQuestion(); }
  }

  clearChat(): void {
    this.messages = [];
    sessionStorage.removeItem(SESSION_KEY);
    this.initWelcome();
  }

  private pushAiMessage(content: string): void {
    this.messages.push({ role: 'ai', content, time: this.now() });
    this.shouldScroll = true;
    this.saveSession();
  }

  private saveSession(): void {
    try {
      const toSave = this.messages.filter(m => !m.loading);
      sessionStorage.setItem(SESSION_KEY, JSON.stringify(toSave));
    } catch { /* sessionStorage plein, on ignore */ }
  }

  private now(): string {
    return new Date().toLocaleTimeString('fr-FR', { hour: '2-digit', minute: '2-digit' });
  }

  private scrollToBottom(): void {
    try { this.chatBottom?.nativeElement.scrollIntoView({ behavior: 'smooth' }); } catch {}
  }

  formatContent(text: string): string {
    return text
      .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
      .replace(/##\s*(.*)/g, '<h4 style="color:#1565C0;margin:10px 0 4px;font-size:13.5px">$1</h4>')
      .replace(/###\s*(.*)/g, '<h5 style="color:#374151;margin:8px 0 3px;font-size:13px">$1</h5>')
      .replace(/\* (.*?)(?=\n|$)/g, '<li style="margin:2px 0;padding-left:4px">$1</li>')
      .replace(/\n/g, '<br>');
  }
}
