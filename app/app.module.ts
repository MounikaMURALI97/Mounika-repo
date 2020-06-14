import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FormsModule} from '@angular/forms';

import { ProductpageComponent } from './productpage/productpage.component';
import { ProfilepageComponent } from './profilepage/profilepage.component';
import { DeliverylocationComponent } from './deliverylocation/deliverylocation.component';
import { PaymentComponent } from './payment/payment.component';
import {CartComponent} from './cart/cart.component';
import{OrderedItemsComponent} from './ordered-items/ordered-items.component';
import { OrdereddetailsComponent } from './ordereddetails/ordereddetails.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { UsermanagementComponent } from './usermanagement/usermanagement.component';
import { ProductmanagementComponent } from './productmanagement/productmanagement.component';
import { CategoryComponent } from './category/category.component';
import { AdminprofileComponent } from './adminprofile/adminprofile.component';
import { SignUpComponent } from './sign-up/sign-up.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    ProductpageComponent,
    ProfilepageComponent,
    DeliverylocationComponent,
    PaymentComponent,
    OrdereddetailsComponent,
    AdminloginComponent,
    AdminhomeComponent,
    UsermanagementComponent,
    ProductmanagementComponent,
    CategoryComponent,
    AdminprofileComponent,
    SignUpComponent,
    OrderedItemsComponent,
    CartComponent, 
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
