
import { Exercise } from "./Exercise.model";

export interface ExerciseTable {
  id?: number;
  name: string;
  description: string;
  exercises : Exercise [];
}

