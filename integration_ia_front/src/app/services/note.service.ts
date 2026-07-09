import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, of } from 'rxjs';
import { map, catchError, switchMap } from 'rxjs/operators';
import { FIND_ALL_INSCRIT, FIND_NOTES_BY_MATRICULE } from 'app/helpers/url.constants';

@Injectable({ providedIn: 'root' })
export class NoteService {
  constructor(private http: HttpClient) {}

  getAllInscrits(): Observable<any[]> {
    return this.http.get<any>(FIND_ALL_INSCRIT).pipe(
      map((r: any) => r?.payload || []),
      catchError(() => of([]))
    );
  }

  getNotesByMatricule(matricule: string): Observable<any[]> {
    return this.http.get<any>(`${FIND_NOTES_BY_MATRICULE}${matricule}`).pipe(
      map((r: any) => r?.payload || []),
      catchError(() => of([]))
    );
  }

  // Load all notes for a list of inscrits, with each note annotated with its inscrit
  getAllNotesByInscrits(inscrits: any[]): Observable<any[]> {
    if (!inscrits || inscrits.length === 0) return of([]);
    const calls = inscrits.map(inscrit =>
      this.getNotesByMatricule(inscrit.matricule).pipe(
        map(notes => notes.map((n: any) => ({ ...n, inscrit })))
      )
    );
    return forkJoin(calls).pipe(
      map((results: any[][]) => ([] as any[]).concat(...results))
    );
  }
}
