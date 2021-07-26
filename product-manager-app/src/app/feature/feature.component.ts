import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { Customer } from '../dtos/customer';
import { Feature } from '../dtos/feature';
import { CustomerService } from '../services/customer.service';
import { FeatureService } from '../services/feature.service';

@Component({
  selector: 'app-feature',
  templateUrl: './feature.component.html',
  styleUrls: ['./feature.component.scss']
})
export class FeatureComponent implements OnInit {

  public features = [] as Feature[];
  public customers = [] as Customer[];
  public formFeature = {} as FormGroup;
  public feature = {} as Feature;
  constructor(private featureService: FeatureService,
    private customerService: CustomerService,
    public fb: FormBuilder) { }

  ngOnInit(): void {
    this.createForm();
    this.loadFeatures();
    this.loadCustomers();
  }

  createForm(){
    this.formFeature = this.fb.group({
      displayName:[''],
      technicalName:['',Validators.required],
      expiresOn:[],
      description:[''],
      inverted:[false,Validators.required],
      customerIds:[],
    })
  }

  saveFeature(){
    this.feature ={
      id: this.feature.id,
      displayName : this.formFeature.value.displayName,
      technicalName :this.formFeature.value.technicalName,
      expiresOn:this.formFeature.value.expiresOn ? this.formFeature.value.expiresOn.toDate() : null,
      description:this.formFeature.value.description,
      inverted:this.formFeature.value.inverted,
      customerIds: this.formFeature.value.customerIds
    }
    console.log( this.feature);
    if(this.feature.id){
      this.featureService.updateFeature(this.feature).subscribe(()=>{
        this.loadFeatures();
      });
    }else{
      this.featureService.saveFeature(this.feature).subscribe(()=>{
        this.loadFeatures();
      });
    }

  }

  loadFeatures(){
    this.featureService.getFeatures().subscribe((features: any)=>{
      this.features = features.data as Feature[];
      this.clearForm();
    });
  }

  loadCustomers(){
    this.customerService.getCustomers().subscribe((customers: any)=>{
      this.customers = customers.data as Customer[];
      this.clearForm();
    });
  }

  clearForm(){
    this.feature = {} as Feature;
    this.formFeature.reset();
    this.formFeature.controls['inverted'].setValue(false);
  }

  edit(feature: any){
    this.feature = feature;
    this.formFeature = this.fb.group({
      displayName:[feature.displayName],
      technicalName:[feature.technicalName,Validators.required],
      expiresOn:[moment(feature.expiresOn)],
      description:[feature.description],
      inverted:[feature.inverted,Validators.required],
      customerIds:[feature.customerIds],
    });
  }

  archive(feature: any){
    feature.archived = true;
    feature.expiresOn = new Date();
    this.featureService.updateFeature(feature).subscribe(()=>{
      this.loadFeatures();
    });
  }
}
