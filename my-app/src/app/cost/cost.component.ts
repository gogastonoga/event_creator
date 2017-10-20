import { Component, OnInit, Input, ChangeDetectorRef, NgZone, ChangeDetectionStrategy } from '@angular/core';
import { ContentService } from '../content/content.service';
import { CostService } from './cost.service';
import { ReactiveFormsModule, FormsModule, FormBuilder, Validators, FormGroup , FormControl} from '@angular/forms';

export class HomePageDto {
  description: string;
  backgroundVideoUrl: string;
  backgroundImageUrl: string;
  contactRequestFormat: string;
}
@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrls: ['./cost.component.css'],
  providers: [CostService],
})
export class CostComponent implements OnInit {

  message;
  costSettings;
  errorString: string;
  responseStatus: Object = [];
  returnMsg: String;
  editStatus: boolean = false;
  costForm: FormGroup;

  constructor(private _costService: CostService, private fb: FormBuilder) {
  }

  createForm() {
    this.costForm = this.fb.group({
        accommodationPrice: '',
        mealPrice: '',
        trainingPrice: '',
        discount: '',
        margin: '',
    });
}

  ngOnInit() {
    this.getCostSettings();
    this.createForm();
  }

  getCostSettings = () => {
    this._costService.getCostSettings()
      .subscribe(
      items => { this.costSettings = items; },
      error => this.errorString = <any>error
      );
  }

  editCostSettings() {
      for (let i in this.costForm.value) {
          if (this.costForm.value[i] === ''){
              console.log(this.costForm.value[i]);
              delete 
console.log(i);
          }
      }
    const formModel = this.costForm.value;
    this._costService.editCostSettings(formModel).subscribe(
      data => console.log(this.responseStatus = data),
      err => {this.message = 'Wystąpił problem podczas dodania użytkownika.'},
      () => this.message = 'Użytkownik został dodany.'
    );
  }



}