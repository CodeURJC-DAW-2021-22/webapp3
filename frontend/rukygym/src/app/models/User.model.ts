import { Activity } from './Activity.model';

export interface User {
  id?: number;
  name: string;
  surname: string;
  NIF: string;
  email: string;
  address: string;
  postalCode: string;
  phone: string;
  description: string;
  userType: string;

  height?: string;
  weight?: string;
  medicalInfo?: string;
  ACT1?: Activity;
  ACT2?: Activity;
  ACT3?: Activity;
}