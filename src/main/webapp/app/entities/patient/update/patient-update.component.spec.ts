jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { PatientService } from '../service/patient.service';
import { IPatient, Patient } from '../patient.model';
import { INutritionState } from 'app/entities/nutrition-state/nutrition-state.model';
import { NutritionStateService } from 'app/entities/nutrition-state/service/nutrition-state.service';
import { IActivityLevel } from 'app/entities/activity-level/activity-level.model';
import { ActivityLevelService } from 'app/entities/activity-level/service/activity-level.service';

import { PatientUpdateComponent } from './patient-update.component';

describe('Component Tests', () => {
  describe('Patient Management Update Component', () => {
    let comp: PatientUpdateComponent;
    let fixture: ComponentFixture<PatientUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let patientService: PatientService;
    let nutritionStateService: NutritionStateService;
    let activityLevelService: ActivityLevelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [PatientUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(PatientUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PatientUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      patientService = TestBed.inject(PatientService);
      nutritionStateService = TestBed.inject(NutritionStateService);
      activityLevelService = TestBed.inject(ActivityLevelService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call NutritionState query and add missing value', () => {
        const patient: IPatient = { id: 'CBA' };
        const nutritionState: INutritionState = { id: '17d4175f-af8f-4a55-aecf-b35da6cea4e5' };
        patient.nutritionState = nutritionState;

        const nutritionStateCollection: INutritionState[] = [{ id: 'd01934ed-8b68-4fc1-85cb-e01e909851ce' }];
        jest.spyOn(nutritionStateService, 'query').mockReturnValue(of(new HttpResponse({ body: nutritionStateCollection })));
        const additionalNutritionStates = [nutritionState];
        const expectedCollection: INutritionState[] = [...additionalNutritionStates, ...nutritionStateCollection];
        jest.spyOn(nutritionStateService, 'addNutritionStateToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ patient });
        comp.ngOnInit();

        expect(nutritionStateService.query).toHaveBeenCalled();
        expect(nutritionStateService.addNutritionStateToCollectionIfMissing).toHaveBeenCalledWith(
          nutritionStateCollection,
          ...additionalNutritionStates
        );
        expect(comp.nutritionStatesSharedCollection).toEqual(expectedCollection);
      });

      it('Should call ActivityLevel query and add missing value', () => {
        const patient: IPatient = { id: 'CBA' };
        const activityLevel: IActivityLevel = { id: '532763bd-b3c9-4cd0-b444-431025666840' };
        patient.activityLevel = activityLevel;

        const activityLevelCollection: IActivityLevel[] = [{ id: '0201981f-4129-4be0-928a-12d20d68e486' }];
        jest.spyOn(activityLevelService, 'query').mockReturnValue(of(new HttpResponse({ body: activityLevelCollection })));
        const additionalActivityLevels = [activityLevel];
        const expectedCollection: IActivityLevel[] = [...additionalActivityLevels, ...activityLevelCollection];
        jest.spyOn(activityLevelService, 'addActivityLevelToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ patient });
        comp.ngOnInit();

        expect(activityLevelService.query).toHaveBeenCalled();
        expect(activityLevelService.addActivityLevelToCollectionIfMissing).toHaveBeenCalledWith(
          activityLevelCollection,
          ...additionalActivityLevels
        );
        expect(comp.activityLevelsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const patient: IPatient = { id: 'CBA' };
        const nutritionState: INutritionState = { id: '86be634f-4bcd-4e02-8cf4-f100e6dca7d2' };
        patient.nutritionState = nutritionState;
        const activityLevel: IActivityLevel = { id: '5ea13151-103b-48d4-81f3-aa58479eae2f' };
        patient.activityLevel = activityLevel;

        activatedRoute.data = of({ patient });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(patient));
        expect(comp.nutritionStatesSharedCollection).toContain(nutritionState);
        expect(comp.activityLevelsSharedCollection).toContain(activityLevel);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Patient>>();
        const patient = { id: 'ABC' };
        jest.spyOn(patientService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ patient });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: patient }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(patientService.update).toHaveBeenCalledWith(patient);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Patient>>();
        const patient = new Patient();
        jest.spyOn(patientService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ patient });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: patient }));
        saveSubject.complete();

        // THEN
        expect(patientService.create).toHaveBeenCalledWith(patient);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Patient>>();
        const patient = { id: 'ABC' };
        jest.spyOn(patientService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ patient });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(patientService.update).toHaveBeenCalledWith(patient);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackNutritionStateById', () => {
        it('Should return tracked NutritionState primary key', () => {
          const entity = { id: 'ABC' };
          const trackResult = comp.trackNutritionStateById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackActivityLevelById', () => {
        it('Should return tracked ActivityLevel primary key', () => {
          const entity = { id: 'ABC' };
          const trackResult = comp.trackActivityLevelById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
