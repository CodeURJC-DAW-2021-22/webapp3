import { Activity } from './Activity.model';

export interface Book {
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
  height?: number;
  weight?: number;
  medicalInfo?: string;
  ACT1?: Activity;
  ACT2?: Activity;
  ACT3?: Activity;
}
