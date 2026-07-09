import { PageService } from './../../service/page.service';
import { FlatTreeControl, NestedTreeControl } from '@angular/cdk/tree';
import { Injectable, Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatTreeFlatDataSource, MatTreeFlattener } from '@angular/material/tree';
import { TreeViewMetaData } from '../treeView.model';
import { BehaviorSubject } from 'rxjs';
import { SelectionModel } from '@angular/cdk/collections';
import { MatDialog } from '@angular/material/dialog';
import { NewPageComponent } from 'app/user-module/components/page/new-page/new-page.component';
import { DeleteVo } from 'app/user-module/class/DeleteVo';


export class Node {
  children: Node[];
  item: any;
}
export class FlatNode {
  item: any;
  level: number;
  expandable: boolean;
}

@Component({
  selector: 'app-treeView',
  templateUrl: './treeView.component.html',
  styleUrls: ['./treeView.component.scss']
})

export class TreeViewComponent implements OnInit {

  rootNode: Node[];
  isLoaded: boolean = false;
  dataChange: BehaviorSubject<Node[]> = new BehaviorSubject<Node[]>([]);



  /** Map from flat node to nested node. This helps us finding the nested node to be modified */
  flatNodeMap = new Map<FlatNode, Node>();

  /** Map from nested node to flattened node. This helps us to keep the same object for selection */
  nestedNodeMap = new Map<Node, FlatNode>();

  /** A selected parent node to be inserted */
  selectedParent: FlatNode | null = null;

  treeControl: FlatTreeControl<FlatNode>;

  treeFlattener: MatTreeFlattener<Node, FlatNode>;

  dataSource: MatTreeFlatDataSource<Node, FlatNode>;

  getLevel = (node: FlatNode) => node.level;

  isExpandable = (node: FlatNode) => node.expandable;

  getChildren = (node: Node): Node[] => node.children;

  hasChild = (_: number, _nodeData: FlatNode) => _nodeData.expandable;

  hasNoContent = (_: number, _nodeData: FlatNode) => _nodeData.item === '';

  @Input() sentData: TreeViewMetaData = new TreeViewMetaData();

  constructor(public dialog: MatDialog, private pageService: PageService) {
    this.treeFlattener = new MatTreeFlattener(this.transformer, this.getLevel,
      this.isExpandable, this.getChildren);
    this.treeControl = new FlatTreeControl<FlatNode>(this.getLevel, this.isExpandable);
    this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);
  }

  ngOnInit(): void {
    console.log('SENTDATA', this.sentData);
    this.buildTree();
  }


  buildTree() {
    this.dataChange.subscribe(data => this.dataSource.data = data);

    this.rootNode = [];
    this.sentData.data.forEach(element => {
      if (element[this.sentData.parentRow] == null) {
        this.rootNode.push(this.prepareData(element))
      }
    });
    this.dataChange.next(this.rootNode);
  }

  prepareData(row: any): Node {
    var node: Node;
    node = new Node();
    node.item = row;
    node.children = [];
    this.sentData.data.forEach(value => {
      if (value[this.sentData.parentRow] != null && value[this.sentData.parentRow][this.sentData.rowKey] == row[this.sentData.rowKey]) {
        node.children.push(this.prepareData(value))
      }
    });
    return node;
  }


  /**
  * Transformer to convert nested node to flat node. Record the nodes in maps for later use.
  */
  transformer = (node: Node, level: number) => {
    const existingNode = this.nestedNodeMap.get(node);
    const flatNode = existingNode && existingNode.item[this.sentData.rowKey] === node.item[this.sentData.rowKey]
      ? existingNode
      : new FlatNode();
    flatNode.item = node.item;
    flatNode.level = level;
    flatNode.expandable = !!node.children?.length;
    this.flatNodeMap.set(flatNode, node);
    this.nestedNodeMap.set(node, flatNode);
    return flatNode;
  }



  /** The selection for checklist */
  checklistSelection = new SelectionModel<FlatNode>(true /* multiple */);




  /** Whether all the descendants of the node are selected. */
  descendantsAllSelected(node: FlatNode): boolean {
    const descendants = this.treeControl.getDescendants(node);
    const descAllSelected = descendants.length > 0 && descendants.every(child => {
      return this.checklistSelection.isSelected(child);
    });
    return descAllSelected;
  }

  /** Whether part of the descendants are selected */
  descendantsPartiallySelected(node: FlatNode): boolean {
    const descendants = this.treeControl.getDescendants(node);
    const result = descendants.some(child => this.checklistSelection.isSelected(child));
    return result && !this.descendantsAllSelected(node);
  }
  /** Toggle the to-do item selection. Select/deselect all the descendants node */
  todoItemSelectionToggle(node: FlatNode): void {
    this.checklistSelection.toggle(node);
    const descendants = this.treeControl.getDescendants(node);
    this.checklistSelection.isSelected(node)
      ? this.checklistSelection.select(...descendants)
      : this.checklistSelection.deselect(...descendants);

    // Force update for the parent
    descendants.forEach(child => this.checklistSelection.isSelected(child));
    this.checkAllParentsSelection(node);
  }
  /** Toggle a leaf to-do item selection. Check all the parents to see if they changed */
  todoLeafItemSelectionToggle(node: FlatNode): void {
    this.checklistSelection.toggle(node);
    this.checkAllParentsSelection(node);
  }
  /* Checks all the parents when a leaf node is selected/unselected */
  checkAllParentsSelection(node: FlatNode): void {
    let parent: FlatNode | null = this.getParentNode(node);
    while (parent) {
      this.checkRootNodeSelection(parent);
      parent = this.getParentNode(parent);
    }
  }
  /** Check root node checked state and change it accordingly */
  checkRootNodeSelection(node: FlatNode): void {
    const nodeSelected = this.checklistSelection.isSelected(node);
    const descendants = this.treeControl.getDescendants(node);
    const descAllSelected = descendants.length > 0 && descendants.every(child => {
      return this.checklistSelection.isSelected(child);
    });
    if (nodeSelected && !descAllSelected) {
      this.checklistSelection.deselect(node);
    } else if (!nodeSelected && descAllSelected) {
      this.checklistSelection.select(node);
    }
  }
  /* Get the parent node of a node */
  getParentNode(node: FlatNode): FlatNode | null {
    const currentLevel = this.getLevel(node);

    if (currentLevel < 1) {
      return null;
    }

    const startIndex = this.treeControl.dataNodes.indexOf(node) - 1;

    for (let i = startIndex; i >= 0; i--) {
      const currentNode = this.treeControl.dataNodes[i];

      if (this.getLevel(currentNode) < currentLevel) {
        return currentNode;
      }
    }
    return null;
  }
  edit(row) {
    switch (this.sentData.type) {
      case 'PAGE':
        this.dialog.open(NewPageComponent, {
          width: '900px',
          data: {page:row}
        });
      break;
    }
  }

  add() {
    switch (this.sentData.type) {
      case 'PAGE':
        this.dialog.open(NewPageComponent, {
          width: '900px',
        });
      break;
    }
  }
  addWithFather(row: any) {
    switch (this.sentData.type) {
      case 'PAGE':
        this.dialog.open(NewPageComponent, {
          width: '900px',
          data: {father : row}
          });
          break;
        }
  }

  delete() {

    const deleteVo: DeleteVo = new DeleteVo();
    deleteVo.deleteDate = new Date();
    deleteVo.deleterCode = 'bill';
    console.log(this.checklistSelection.selected);
    this.checklistSelection.selected.forEach(ps => {
      deleteVo.codes.push(ps.item[this.sentData.rowKey]);
    });
    console.log(deleteVo);

    switch (this.sentData.type) {
      case 'PAGE':
        this.pageService.delete(deleteVo).subscribe(result => location.reload());
        break;
    }
    }

}