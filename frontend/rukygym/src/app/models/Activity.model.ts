export interface Activity {
  id?: number;
  name: string;
  room: string;
  capacity: number;
  description: string;
  price: number;
  monday?: string;
  tuesday?: string;
  wednesday?: string;
  thursday?: string;
  friday?: string;
  monitorName?: string;
}
