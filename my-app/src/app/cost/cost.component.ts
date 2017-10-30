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

  costSettings: Cost;
  errorString: string;
  responseStatus: Object = [];
  editStatus: boolean = false;

  constructor(private _costService: CostService, private fb: FormBuilder) {
  }

  ngOnInit() {
    this.getCostSettings();
    this.costSettings = new Cost();
    $('#editCost').hide();
  }

  getCostSettings = () => {
    this._costService.getCostSettings()
      .subscribe(
      costSettings => { this.costSettings = costSettings; },
      error => this.errorString = <any>error
      );
  }

  editCostSettings() {
    this._costService.editCostSettings(this.costSettings).subscribe(
      data => console.log(this.responseStatus = data),
      err => console.log(err),
      () => $('#editCost').slideDown()
    );
  }
}