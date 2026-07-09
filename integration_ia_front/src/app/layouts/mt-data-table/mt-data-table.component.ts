import { SelectionModel } from "@angular/cdk/collections";
import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { MatPaginator } from "@angular/material/paginator";
import { MatSnackBar } from "@angular/material/snack-bar";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { DeleteVo } from "app/class/DeleteVo";
import { Utilisateur } from "app/user-module/class/Utilisateur";
import { ConfirmComponent } from "app/user-module/layouts/confirm/confirm.component";
import { TokenStorageService } from "app/user-module/service/token-storage.service";
import Swal from "sweetalert2";
import { DataTableMetaData } from "../datatable.model";
import { ExcelServiceService } from "app/services/excel-service.service";
import { PromptService } from "app/services/prompt.service";
import { UtilisateurService } from "app/services/utilisateur.service";
import { InscritService } from "app/services/inscrit.service";
import { LiaisonService } from "app/services/liaison.service";
import { DonneesService } from "app/services/donnees.service";
import { CinematiqueService } from "app/services/cinematique.service";
import { TemplatePromptService } from "app/services/template-prompt.service";
import { ReponseService } from "app/services/reponse.service";
import { NewPromptComponent } from "app/ui/prompt/new-prompt/new-prompt.component";
import { NewTemplatePromptComponent } from "app/ui/template-prompt/new-template-prompt/new-template-prompt.component";
import { NewCinematiqueComponent } from "app/ui/cinematique/new-cinematique/new-cinematique.component";
import { NewDonneesComponent } from "app/ui/donnees/new-donnees/new-donnees.component";
import { NewInscritComponent } from "app/ui/inscrit/new-inscrit/new-inscrit.component";
import { NewLiaisonComponent } from "app/ui/liaison/new-liaison/new-liaison.component";
import { NewUtilisateurComponent } from "app/ui/utilisateur/new-utilisateur/new-utilisateur.component";
import { UploadDonneesComponent } from "app/ui/donnees/upload-donnees/upload-donnees.component";
import { NewParamsComponent } from "app/ui/params/new-params/new-params.component";
import { NewTypeCinematiqueComponent } from "app/ui/type-cinematique/new-type-cinematique/new-type-cinematique.component";
import { TypeCinematiqueService } from "app/services/type-cinematique.service";
import { ServiceService } from "app/services/service.service";
import { NewServiceComponent } from "app/ui/service/new-service/new-service.component";
import { NewReponseComponent } from "app/ui/reponse/new-reponse/new-reponse.component";
import { NiveauService } from "app/services/niveau.service";
import { NewNiveauComponent } from "app/ui/niveau/new-niveau/new-niveau.component";
import { UtilisateurClassService } from "app/services/utilisateur-class.service";
import { NewUtilisateurClassComponent } from "app/ui/utilisateur-class/new-utilisateur-class/new-utilisateur-class.component";
import { ParametreService } from "app/services/parametre.service";
import { UploadInscritComponent } from "app/ui/inscrit/upload-inscrit/upload-inscrit.component";
import { UploadNiveauComponent } from "app/ui/niveau/upload-niveau/upload-niveau.component";
import { NotificationService } from "app/services/notification.service";

@Component({
  selector: "app-mt-data-table",
  templateUrl: "./mt-data-table.component.html",
  styleUrls: ["./mt-data-table.component.css"],
})
export class MtDataTableComponent implements OnInit {
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @Input() datatable: DataTableMetaData = new DataTableMetaData();
  @Input() size: string = "400px";
  @Output() messageEvent = new EventEmitter<any>();

  columnsToDisplay: string[] = [];
  headActionsToDisplay: string[] = [];
  rowActionsToDisplay: string[] = [];
  isSelection = false;
  selection = new SelectionModel<any>(true, []);
  data = new MatTableDataSource<any>();
  isDataLoaded: boolean = false;
  d: any = [{}];
  showSearch: boolean = false;
  showSelect: boolean = false;
  user: Utilisateur;
  userConnected: any = {};
  filtreImp: string = "";
  middleData: any = [{}];

  width = window.screen.width;
  height = window.screen.height;

  constructor(
    private _snackBar: MatSnackBar,
    private tokenService: TokenStorageService,
    public dialog: MatDialog,
    private utilisateurClassService: UtilisateurClassService,
    private excelService: ExcelServiceService,
    private utilisateurService: UtilisateurService,
    private promptService: PromptService,
    private liaisonService: LiaisonService,
    private inscritService: InscritService,
    private donneesService: DonneesService,
    private cinematiqueService: CinematiqueService,
    private typeCinematiqueService: TypeCinematiqueService,
    private templatePromptService: TemplatePromptService,
    private reponseService: ReponseService,
    private parametreService: ParametreService,
    private serviceService: ServiceService,
    private niveauService: NiveauService,
    private notificationService: NotificationService,
  ) {}

  ngOnInit() {
    this.initDataTable();
    this.userConnected = this.tokenService.getUser();
  }

  initDataTable() {
    let pos: number;
    this.datatable.colonnes.forEach((element, index) => {
      if (element.label === "select") {
        this.showSelect = true;
        pos = index;
      }
      this.columnsToDisplay.push(element.label);
    });
    this.datatable.colonnes.splice(pos, 1);
    this.headActionsToDisplay = this.datatable.headActions;
    this.rowActionsToDisplay = this.datatable.rowActions;

    if (this.rowActionsToDisplay.length > 0) {
      this.columnsToDisplay.push("rowActions");
    }
    this.data = new MatTableDataSource<any>(this.datatable.data);
    this.data.paginator = this.paginator;
    this.data.sort = this.sort;
    this.isDataLoaded = true;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.data.filter = filterValue.trim().toLowerCase();
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.data.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.data.data.forEach((row) => this.selection.select(row));
  }

  checkboxLabel(row?: any): string {
    if (!row) {
      return `${this.isAllSelected() ? "select" : "deselect"} all`;
    }
    return `${this.selection.isSelected(row) ? "deselect" : "select"} row ${row.position + 1}`;
  }

  edit(row) {
    const value = row;
    switch (this.datatable.type) {
      case "NIVEAU":
        this.dialog.open(NewNiveauComponent, { width: `${this.width}px`, data: value });
        break;
      case "TYPECINEMATIQUE":
        this.dialog.open(NewTypeCinematiqueComponent, { width: `${this.width}px`, data: value });
        break;
      case "SERVICE":
        this.dialog.open(NewServiceComponent, { width: `${this.width}px`, data: value });
        break;
      case "REPONSE":
        this.dialog.open(NewReponseComponent, { width: `${this.width}px`, data: value });
        break;
      case "UTILISATEUR":
        this.dialog.open(NewUtilisateurComponent, { width: "900px", data: value });
        break;
      case "UTILISATEURCLASS":
        this.dialog.open(NewUtilisateurClassComponent, { width: "900px", data: value });
        break;
      case "PROMPT":
        this.dialog.open(NewPromptComponent, { width: `${this.width}px`, data: value });
        break;
      case "LIAISON":
        this.dialog.open(NewLiaisonComponent, { width: `${this.width}px`, data: value });
        break;
      case "INSCRIT":
        this.dialog.open(NewInscritComponent, { width: `${this.width}px`, data: value });
        break;
      case "DONNEES":
        this.dialog.open(NewDonneesComponent, { width: `${this.width}px`, data: value });
        break;
      case "CINEMATIQUE":
        this.dialog.open(NewCinematiqueComponent, { width: `${this.width}px`, data: value });
        break;
      case "TEMPLATEPROMPT":
        this.dialog.open(NewTemplatePromptComponent, { width: `${this.width}px`, data: value });
        break;
      case "PARAMETRE":
        this.dialog.open(NewParamsComponent, { width: `${this.width}px`, data: value });
        break;
    }
  }

  add() {
    switch (this.datatable.type) {
      case "SERVICE":
        this.dialog.open(NewServiceComponent, { width: "800px" });
        break;
      case "REPONSE":
        this.dialog.open(NewReponseComponent, { width: "800px" });
        break;
      case "TYPECINEMATIQUE":
        this.dialog.open(NewTypeCinematiqueComponent, { width: "800px" });
        break;
      case "UTILISATEUR":
        this.dialog.open(NewUtilisateurComponent, { width: "900px" });
        break;
      case "UTILISATEURCLASS":
        this.dialog.open(NewUtilisateurClassComponent, { width: "900px" });
        break;
      case "PROMPT":
        this.dialog.open(NewPromptComponent, { width: "800px" });
        break;
      case "LIAISON":
        this.dialog.open(NewLiaisonComponent, { width: "800px" });
        break;
      case "NIVEAU":
        this.dialog.open(NewNiveauComponent, { width: "800px" });
        break;
      case "INSCRIT":
        this.dialog.open(NewInscritComponent, { width: "800px" });
        break;
      case "DONNEES":
        this.dialog.open(NewDonneesComponent, { width: "800px" });
        break;
      case "CINEMATIQUE":
        this.dialog.open(NewCinematiqueComponent, { width: "800px" });
        break;
      case "TEMPLATEPROMPT":
        this.dialog.open(NewTemplatePromptComponent, { width: "800px" });
        break;
      case "PARAMETRE":
        this.dialog.open(NewParamsComponent, { width: "800px" });
        break;
    }
  }

  delete() {
    let message: string;
    const deleteVo: DeleteVo = new DeleteVo();
    deleteVo.deleteDate = new Date();
    deleteVo.deleterCode = this.userConnected.codeUtilisateur;

    const confirmDelete = (codeField: string, singular: string, plural: string, svc: any) => {
      this.selection.selected.forEach((ps) => deleteVo.codes.push(ps[codeField]));
      message = this.selection.selected.length >= 2 ? plural : singular;
      Swal.fire({
        title: "Confirmation", text: message, icon: "warning",
        showCancelButton: true, cancelButtonColor: "#3085d6",
        confirmButtonColor: "#ff0000", confirmButtonText: "Supprimer", cancelButtonText: "Annuler",
      }).then((result) => {
        if (result.isConfirmed) {
          svc.delete(deleteVo).subscribe(
            (r: any) => {
              this.middleData = r;
              if (this.middleData.status === "OK") this.alerter("Success !", "success", this.middleData.message);
              else this.alerter("Erreur !", "error", this.middleData.message);
            },
            () => this.alerter("Erreur !", "error", "Impossible de contacter le serveur !")
          );
        }
      });
    };

    switch (this.datatable.type) {
      case "SERVICE":
        confirmDelete("codeService", "Supprimer le service sélectionné ?", "Supprimer les services sélectionnés ?", this.serviceService);
        break;
      case "REPONSE":
        confirmDelete("codeReponse", "Supprimer la réponse sélectionnée ?", "Supprimer les réponses sélectionnées ?", this.reponseService);
        break;
      case "TYPECINEMATIQUE":
        confirmDelete("codeTypeCinematique", "Supprimer le type sélectionné ?", "Supprimer les types sélectionnés ?", this.typeCinematiqueService);
        break;
      case "PARAMETRE":
        confirmDelete("codeParametre", "Supprimer le paramètre sélectionné ?", "Supprimer les paramètres sélectionnés ?", this.parametreService);
        break;
      case "UTILISATEUR":
        confirmDelete("codeUtilisateur", "Supprimer l'utilisateur sélectionné ?", "Supprimer les utilisateurs sélectionnés ?", this.utilisateurService);
        break;
      case "UTILISATEURCLASS":
        confirmDelete("codeUtilisateur", "Supprimer l'utilisateur sélectionné ?", "Supprimer les utilisateurs sélectionnés ?", this.utilisateurClassService);
        break;
      case "PROMPT":
        confirmDelete("codePrompt", "Supprimer le prompt sélectionné ?", "Supprimer les prompts sélectionnés ?", this.promptService);
        break;
      case "LIAISON":
        confirmDelete("codeLiaison", "Supprimer la liaison sélectionnée ?", "Supprimer les liaisons sélectionnées ?", this.liaisonService);
        break;
      case "NIVEAU":
        confirmDelete("codeNiveau", "Supprimer le niveau sélectionné ?", "Supprimer les niveaux sélectionnés ?", this.niveauService);
        break;
      case "DONNEES":
        confirmDelete("codeDonnees", "Supprimer la donnée sélectionnée ?", "Supprimer les données sélectionnées ?", this.donneesService);
        break;
      case "CINEMATIQUE":
        confirmDelete("codeCinematique", "Supprimer la cinématique sélectionnée ?", "Supprimer les cinématiques sélectionnées ?", this.cinematiqueService);
        break;
      case "TEMPLATEPROMPT":
        confirmDelete("codeTemplatePrompt", "Supprimer le template sélectionné ?", "Supprimer les templates sélectionnés ?", this.templatePromptService);
        break;
      case "INSCRIT":
        confirmDelete("codeInscrit", "Supprimer l'inscrit sélectionné ?", "Supprimer les inscrits sélectionnés ?", this.inscritService);
        break;
    }
  }

  deleteRow(row: any) {
    const deleteVo: DeleteVo = new DeleteVo();
    deleteVo.deleteDate = new Date();
    deleteVo.deleterCode = this.userConnected.codeUtilisateur;

    const doDelete = (codeField: string, svc: any) => {
      deleteVo.codes.push(row[codeField]);
      Swal.fire({
        title: 'Confirmation', text: 'Supprimer cet élément ?', icon: 'warning',
        showCancelButton: true, cancelButtonColor: '#3085d6',
        confirmButtonColor: '#ff0000', confirmButtonText: 'Supprimer', cancelButtonText: 'Annuler',
      }).then((result) => {
        if (result.isConfirmed) {
          svc.delete(deleteVo).subscribe(
            (r: any) => {
              this.middleData = r;
              if (this.middleData.status === 'OK') this.alerter('Succès !', 'success', this.middleData.message);
              else this.alerter('Erreur !', 'error', this.middleData.message);
            },
            () => this.alerter('Erreur !', 'error', 'Impossible de contacter le serveur !')
          );
        }
      });
    };

    switch (this.datatable.type) {
      case 'UTILISATEURCLASS': doDelete('codeUtilisateur', this.utilisateurClassService); break;
      case 'UTILISATEUR': doDelete('codeUtilisateur', this.utilisateurService); break;
      case 'NIVEAU': doDelete('codeNiveau', this.niveauService); break;
      case 'INSCRIT': doDelete('codeInscrit', this.inscritService); break;
      case 'LIAISON': doDelete('codeLiaison', this.liaisonService); break;
      case 'DONNEES': doDelete('codeDonnees', this.donneesService); break;
      case 'CINEMATIQUE': doDelete('codeCinematique', this.cinematiqueService); break;
      case 'PROMPT': doDelete('codePrompt', this.promptService); break;
      case 'TEMPLATEPROMPT': doDelete('codeTemplatePrompt', this.templatePromptService); break;
      case 'TYPECINEMATIQUE': doDelete('codeTypeCinematique', this.typeCinematiqueService); break;
      case 'SERVICE': doDelete('codeService', this.serviceService); break;
      case 'REPONSE': doDelete('codeReponse', this.reponseService); break;
      case 'PARAMETRE': doDelete('codeParametre', this.parametreService); break;
    }
  }

  desactivate() {}

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  upload() {
    switch (this.datatable.type) {
      case "DONNEES":
        this.dialog.open(UploadDonneesComponent, { width: "900px" });
        break;
      case "INSCRIT":
        this.dialog.open(UploadInscritComponent, { width: "900px" });
        break;
      case "NIVEAU":
        this.dialog.open(UploadNiveauComponent, { width: "900px" });
        break;
    }
  }

  openConfirmDialog(libelleMsg: string): any {
    return this.dialog.open(ConfirmComponent, {
      width: "500px",
      data: { libelle: libelleMsg },
    });
  }

  exportExcel(datatable: any): void {
    this.excelService.exportAsExcelFile(datatable.data, datatable.type?.toLowerCase() || "data");
  }

  alerter(message, icon, texte) {
    const notifType = icon === 'success' ? 'success' : icon === 'error' ? 'error' : 'warning';
    this.notificationService.add(message, texte, notifType);
    Swal.fire({
      title: message, text: texte, icon: icon,
      showCancelButton: false, confirmButtonColor: "#3085d6", confirmButtonText: "Ok",
    }).then((result) => {
      if (result.isConfirmed) location.reload();
    });
  }
}
