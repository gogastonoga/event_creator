import { Component, OnInit, Input, ChangeDetectorRef, NgZone, ChangeDetectionStrategy } from '@angular/core';
import { ContentService } from '../content/content.service';
import { CostService } from './cost.service';
import { Cost } from './cost';
import { ReactiveFormsModule, FormsModule, FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrls: ['./cost.component.css'],
  providers: [CostService],
})
export class CostComponent implements OnInit {

  message;
  costSettings: Cost;
  errorString: string;
  responseStatus: Object = [];
  editStatus: boolean = false;
  // costForm: FormGroup;

  constructor(private _costService: CostService, private fb: FormBuilder) {
  }

  // createForm() {
  //   this.costForm = this.fb.group({
  //     accommodationPrice: null,
  //     mealPrice: null,
  //     trainingPrice: null,
  //     discount: null,
  //     margin: null,
  //   });
  // }

  ngOnInit() {
    this.getCostSettings();
    this.costSettings = new Cost();
    //this.createForm();
  }

  getCostSettings = () => {
    this._costService.getCostSettings()
      .subscribe(
      costSettings => { this.costSettings = costSettings; },
      error => this.errorString = <any>error
      );
  }

  editCostSettings() {
    // for (let i in this.costForm.value) {
    //   if (this.costForm.value[i] === '') {
    //     console.log(this.costForm.value[i]);
    //     // delete 
    //     //console.log(i);
    //   }
    // }
    // const formModel = this.costForm.value;
    this._costService.editCostSettings(this.costSettings).subscribe(
      data => console.log(this.responseStatus = data)
    );
  }
}