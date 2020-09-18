import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'dynamic-form',
        loadChildren: () => import('./dynamic-form/dynamic-form.module').then(m => m.StartMedsolPrototypeDynamicFormModule),
      },
      {
        path: 'medical-invoice',
        loadChildren: () => import('./medical-invoice/medical-invoice.module').then(m => m.StartMedsolPrototypeMedicalInvoiceModule),
      },
      {
        path: 'medical-service',
        loadChildren: () => import('./medical-service/medical-service.module').then(m => m.StartMedsolPrototypeMedicalServiceModule),
      },
      {
        path: 'nav-settings',
        loadChildren: () => import('./nav-settings/nav-settings.module').then(m => m.StartMedsolPrototypeNavSettingsModule),
      },
      {
        path: 'company',
        loadChildren: () => import('./company/company.module').then(m => m.StartMedsolPrototypeCompanyModule),
      },
      {
        path: 'contact-person',
        loadChildren: () => import('./contact-person/contact-person.module').then(m => m.StartMedsolPrototypeContactPersonModule),
      },
      {
        path: 'provider',
        loadChildren: () => import('./provider/provider.module').then(m => m.StartMedsolPrototypeProviderModule),
      },
      {
        path: 'error-record',
        loadChildren: () => import('./error-record/error-record.module').then(m => m.StartMedsolPrototypeErrorRecordModule),
      },
      {
        path: 'specialists-advice',
        loadChildren: () =>
          import('./specialists-advice/specialists-advice.module').then(m => m.StartMedsolPrototypeSpecialistsAdviceModule),
      },
      {
        path: 'hash-tag',
        loadChildren: () => import('./hash-tag/hash-tag.module').then(m => m.StartMedsolPrototypeHashTagModule),
      },
      {
        path: 'statistics',
        loadChildren: () => import('./statistics/statistics.module').then(m => m.StartMedsolPrototypeStatisticsModule),
      },
      {
        path: 'announcement',
        loadChildren: () => import('./announcement/announcement.module').then(m => m.StartMedsolPrototypeAnnouncementModule),
      },
      {
        path: 'feed-back-message',
        loadChildren: () => import('./feed-back-message/feed-back-message.module').then(m => m.StartMedsolPrototypeFeedBackMessageModule),
      },
      {
        path: 'data-generator',
        loadChildren: () => import('./data-generator/data-generator.module').then(m => m.StartMedsolPrototypeDataGeneratorModule),
      },
      {
        path: 'prescription',
        loadChildren: () => import('./prescription/prescription.module').then(m => m.StartMedsolPrototypePrescriptionModule),
      },
      {
        path: 'prescription-eeszt-id',
        loadChildren: () =>
          import('./prescription-eeszt-id/prescription-eeszt-id.module').then(m => m.StartMedsolPrototypePrescriptionEesztIdModule),
      },
      {
        path: 'ehr-document',
        loadChildren: () => import('./ehr-document/ehr-document.module').then(m => m.StartMedsolPrototypeEhrDocumentModule),
      },
      {
        path: 'external-document',
        loadChildren: () => import('./external-document/external-document.module').then(m => m.StartMedsolPrototypeExternalDocumentModule),
      },
      {
        path: 'medical-case-diagnosis',
        loadChildren: () =>
          import('./medical-case-diagnosis/medical-case-diagnosis.module').then(m => m.StartMedsolPrototypeMedicalCaseDiagnosisModule),
      },
      {
        path: 'performed-procedure',
        loadChildren: () =>
          import('./performed-procedure/performed-procedure.module').then(m => m.StartMedsolPrototypePerformedProcedureModule),
      },
      {
        path: 'procedures-of-praxis',
        loadChildren: () =>
          import('./procedures-of-praxis/procedures-of-praxis.module').then(m => m.StartMedsolPrototypeProceduresOfPraxisModule),
      },
      {
        path: 'case-text-documentation',
        loadChildren: () =>
          import('./case-text-documentation/case-text-documentation.module').then(m => m.StartMedsolPrototypeCaseTextDocumentationModule),
      },
      {
        path: 'cs-diagnosis',
        loadChildren: () => import('./cs-diagnosis/cs-diagnosis.module').then(m => m.StartMedsolPrototypeCsDiagnosisModule),
      },
      {
        path: 'cs-country',
        loadChildren: () => import('./cs-country/cs-country.module').then(m => m.StartMedsolPrototypeCsCountryModule),
      },
      {
        path: 'cs-postal-code',
        loadChildren: () => import('./cs-postal-code/cs-postal-code.module').then(m => m.StartMedsolPrototypeCsPostalCodeModule),
      },
      {
        path: 'code-set-load',
        loadChildren: () => import('./code-set-load/code-set-load.module').then(m => m.StartMedsolPrototypeCodeSetLoadModule),
      },
      {
        path: 'cs-procedure',
        loadChildren: () => import('./cs-procedure/cs-procedure.module').then(m => m.StartMedsolPrototypeCsProcedureModule),
      },
      {
        path: 'dictionary-item',
        loadChildren: () => import('./dictionary-item/dictionary-item.module').then(m => m.StartMedsolPrototypeDictionaryItemModule),
      },
      {
        path: 'dictionary-translation',
        loadChildren: () =>
          import('./dictionary-translation/dictionary-translation.module').then(m => m.StartMedsolPrototypeDictionaryTranslationModule),
      },
      {
        path: 'employee',
        loadChildren: () => import('./employee/employee.module').then(m => m.StartMedsolPrototypeEmployeeModule),
      },
      {
        path: 'billing-information',
        loadChildren: () =>
          import('./billing-information/billing-information.module').then(m => m.StartMedsolPrototypeBillingInformationModule),
      },
      {
        path: 'medical-case',
        loadChildren: () => import('./medical-case/medical-case.module').then(m => m.StartMedsolPrototypeMedicalCaseModule),
      },
      {
        path: 'praxis',
        loadChildren: () => import('./praxis/praxis.module').then(m => m.StartMedsolPrototypePraxisModule),
      },
      {
        path: 'pph-medicine',
        loadChildren: () => import('./pph-medicine/pph-medicine.module').then(m => m.StartMedsolPrototypePphMedicineModule),
      },
      {
        path: 'pph-medicine-qualified-name',
        loadChildren: () =>
          import('./pph-medicine-qualified-name/pph-medicine-qualified-name.module').then(
            m => m.StartMedsolPrototypePphMedicineQualifiedNameModule
          ),
      },
      {
        path: 'pupha-loader',
        loadChildren: () => import('./pupha-loader/pupha-loader.module').then(m => m.StartMedsolPrototypePuphaLoaderModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class StartMedsolPrototypeEntityModule {}
