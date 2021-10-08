import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'nutrition-state',
        data: { pageTitle: 'NutritionStates' },
        loadChildren: () => import('./nutrition-state/nutrition-state.module').then(m => m.NutritionStateModule),
      },
      {
        path: 'activity-level',
        data: { pageTitle: 'ActivityLevels' },
        loadChildren: () => import('./activity-level/activity-level.module').then(m => m.ActivityLevelModule),
      },
      {
        path: 'patient',
        data: { pageTitle: 'Patients' },
        loadChildren: () => import('./patient/patient.module').then(m => m.PatientModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
