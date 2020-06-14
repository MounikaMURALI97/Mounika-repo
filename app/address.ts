import { User } from './user';

export class Address {
   
	addressId:number;	
	street:string;	
	district:string;	
	state:string;	
	country:string;
	pinCode:number;
	userAddress:User;
}
