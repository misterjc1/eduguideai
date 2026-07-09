export class DataTableColonne {
    name: string;
    label: string;
    show: boolean;
  }

export class DataTableMetaData {
    colonnes: DataTableColonne[];
    headActions: string[];
    rowActions: string[];
    data: any [];
    title: string;
    type: string;
  }
