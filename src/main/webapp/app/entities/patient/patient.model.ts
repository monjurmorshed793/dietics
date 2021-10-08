import * as dayjs from 'dayjs';
import { INutritionState } from 'app/entities/nutrition-state/nutrition-state.model';
import { IActivityLevel } from 'app/entities/activity-level/activity-level.model';
import { IDietNature } from 'app/entities/diet-nature/diet-nature.model';
import { ISupplements } from 'app/entities/supplements/supplements.model';
import { Gender } from 'app/entities/enumerations/gender.model';
import { WeightType } from 'app/entities/enumerations/weight-type.model';
import { HeightMeasureType } from 'app/entities/enumerations/height-measure-type.model';
import { GainLossType } from 'app/entities/enumerations/gain-loss-type.model';

export interface IPatient {
  id?: string;
  name?: string | null;
  address?: string | null;
  hospital?: string | null;
  admissionDate?: dayjs.Dayjs | null;
  reasonOfAdmission?: string | null;
  wordNo?: string | null;
  bedNo?: string | null;
  healthCondition?: string | null;
  mentalStatus?: string | null;
  age?: number | null;
  sex?: Gender | null;
  weight?: number | null;
  weightType?: WeightType | null;
  height?: number | null;
  heightMeasureType?: HeightMeasureType | null;
  ibw?: number | null;
  bmi?: number | null;
  recentWeightGainLoss?: boolean | null;
  gainLossMeasure?: number | null;
  gainLossTimeFrame?: number | null;
  gainLossType?: GainLossType | null;
  nutritionState?: INutritionState | null;
  activityLevel?: IActivityLevel | null;
  dietNatures?: IDietNature[] | null;
  supplements?: ISupplements[] | null;
}

export class Patient implements IPatient {
  constructor(
    public id?: string,
    public name?: string | null,
    public address?: string | null,
    public hospital?: string | null,
    public admissionDate?: dayjs.Dayjs | null,
    public reasonOfAdmission?: string | null,
    public wordNo?: string | null,
    public bedNo?: string | null,
    public healthCondition?: string | null,
    public mentalStatus?: string | null,
    public age?: number | null,
    public sex?: Gender | null,
    public weight?: number | null,
    public weightType?: WeightType | null,
    public height?: number | null,
    public heightMeasureType?: HeightMeasureType | null,
    public ibw?: number | null,
    public bmi?: number | null,
    public recentWeightGainLoss?: boolean | null,
    public gainLossMeasure?: number | null,
    public gainLossTimeFrame?: number | null,
    public gainLossType?: GainLossType | null,
    public nutritionState?: INutritionState | null,
    public activityLevel?: IActivityLevel | null,
    public dietNatures?: IDietNature[] | null,
    public supplements?: ISupplements[] | null
  ) {
    this.recentWeightGainLoss = this.recentWeightGainLoss ?? false;
  }
}

export function getPatientIdentifier(patient: IPatient): string | undefined {
  return patient.id;
}
