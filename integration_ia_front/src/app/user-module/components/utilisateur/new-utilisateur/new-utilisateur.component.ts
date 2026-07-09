import { Component, OnInit, Optional, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { UtilisateurService } from 'app/user-module/service/utilisateur.service';
import { ProfilService } from 'app/user-module/service/profil.service';
import { TokenStorageService } from 'app/user-module/service/token-storage.service';
import { Utilisateur } from 'app/user-module/class/Utilisateur';
import { Profil } from 'app/user-module/class/Profil';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-new-utilisateur',
  templateUrl: './new-utilisateur.component.html',
  styleUrls: ['./new-utilisateur.component.scss']
})
export class NewUtilisateurComponent implements OnInit {

  form!: FormGroup;
  profils: Profil[] = [];
  isLoading = false;
  isSaving = false;
  errorMessage = '';
  isEdit = false;

  userTypes = [
    { value: 'AGENT',   label: 'Administrateur (AGENT)' },
    { value: 'INSCRIT', label: 'Étudiant (INSCRIT)' },
    { value: 'TUTEUR',  label: 'Tuteur (TUTEUR)' },
  ];

  constructor(
    private fb: FormBuilder,
    private utilisateurService: UtilisateurService,
    private profilService: ProfilService,
    private tokenService: TokenStorageService,
    private dialogRef: MatDialogRef<NewUtilisateurComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: Utilisateur | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data?.codeUtilisateur;

    this.form = this.fb.group({
      username: [this.data?.username || '', Validators.required],
      password: [this.isEdit ? '' : '', this.isEdit ? [] : [Validators.required, Validators.minLength(6)]],
      email:    [this.data?.email    || '', [Validators.required, Validators.email]],
      phone:    [this.data?.phone    || ''],
      type:     [this.data?.type     || 'INSCRIT', Validators.required],
      profil:   [this.data?.profil   || null],
    });

    this.isLoading = true;
    this.profilService.findAll().pipe(
      catchError(() => of({ payload: [] } as any))
    ).subscribe((res: any) => {
      this.profils = res?.payload || [];
      if (this.isEdit && this.data?.profil) {
        const matched = this.profils.find(p => p.codeProfil === this.data.profil?.codeProfil);
        if (matched) this.form.get('profil')?.setValue(matched);
      }
      this.isLoading = false;
    });
  }

  onSubmit(): void {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }

    this.isSaving = true;
    this.errorMessage = '';
    const userConnected = this.tokenService.getUser();
    const formVal = this.form.value;

    const utilisateur: any = {
      username: formVal.username,
      email:    formVal.email,
      phone:    formVal.phone,
      type:     formVal.type,
      profil:   formVal.profil,
    };

    if (this.isEdit) {
      utilisateur.codeUtilisateur = this.data!.codeUtilisateur;
      utilisateur.updateDate   = new Date();
      utilisateur.updaterCode  = userConnected?.codeUtilisateur;
      if (formVal.password) utilisateur.password = formVal.password;

      this.utilisateurService.update(utilisateur).pipe(
        catchError(() => of({ status: 'ERROR', message: 'Impossible de joindre le serveur.' }))
      ).subscribe((res: any) => {
        this.isSaving = false;
        if (res?.status === 'OK') { this.dialogRef.close(true); location.reload(); }
        else this.errorMessage = res?.message || 'Erreur lors de la modification.';
      });

    } else {
      utilisateur.password     = formVal.password;
      utilisateur.creationDate = new Date();
      utilisateur.creatorCode  = userConnected?.codeUtilisateur;
      utilisateur.deleted      = false;

      this.utilisateurService.signup(utilisateur).pipe(
        catchError(() => of({ status: 'ERROR', message: 'Impossible de joindre le serveur.' }))
      ).subscribe((res: any) => {
        this.isSaving = false;
        if (res?.status === 'OK') { this.dialogRef.close(true); location.reload(); }
        else this.errorMessage = res?.message || 'Erreur lors de la création.';
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }
}
