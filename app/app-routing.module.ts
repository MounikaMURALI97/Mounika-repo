
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { HomeComponent } from './home/home.component';
import { AuthguardService } from './authguard.service';
import { ProfilepageComponent } from './profilepage/profilepage.component';
import { ProductpageComponent } from './productpage/productpage.component';
import { DeliverylocationComponent } from './deliverylocation/deliverylocation.component';
import { PaymentComponent } from './payment/payment.component';
import { OrdereddetailsComponent } from './ordereddetails/ordereddetails.component';
import { CartComponent } from './cart/cart.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { AdminauthguardService } from './adminauthguard.service';
import { UserService } from './user.service';
import { UsermanagementComponent } from './usermanagement/usermanagement.component';
import { ProductmanagementComponent } from './productmanagement/productmanagement.component';
import { CategoryComponent } from './category/category.component';
import { AdminprofileComponent } from './adminprofile/adminprofile.component';
import { OrderedItemsComponent} from './ordered-items/ordered-items.component'




const routes: Routes = [ 
{path:'',component:LoginComponent},
{path:'signUp',component:SignUpComponent},
{path:'home',component:HomeComponent, canActivate: [AuthguardService]},
{path:'profilepage',component:ProfilepageComponent, canActivate: [AuthguardService]},
{path:'productpage',component:ProductpageComponent, canActivate: [AuthguardService]},
{path:'deliverylocation',component:DeliverylocationComponent, canActivate: [AuthguardService]},
{path:'payment',component:PaymentComponent, canActivate: [AuthguardService]},
{path:'orders',component:OrdereddetailsComponent, canActivate: [AuthguardService]},
{path:'cart',component:CartComponent,canActivate: [AuthguardService]},
{path:'adminlogin',component:AdminloginComponent},
{path:'adminhome',component:AdminhomeComponent, canActivate: [AdminauthguardService]},
{path:'usermanagment',component:UsermanagementComponent, canActivate: [AdminauthguardService]},
{path:'productmanagement',component:ProductmanagementComponent, canActivate: [AdminauthguardService]},
{path:'categorymanagement',component:CategoryComponent, canActivate: [AdminauthguardService]},
{path:'ordersmanagement',component:OrderedItemsComponent,canActivate: [AdminauthguardService]},
{path:'adminprofile',component:AdminprofileComponent, canActivate: [AdminauthguardService]}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


