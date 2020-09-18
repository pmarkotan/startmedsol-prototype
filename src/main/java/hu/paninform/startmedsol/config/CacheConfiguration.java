package hu.paninform.startmedsol.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, hu.paninform.startmedsol.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, hu.paninform.startmedsol.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, hu.paninform.startmedsol.domain.User.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Authority.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.User.class.getName() + ".authorities");
            createCache(cm, hu.paninform.startmedsol.domain.DynamicForm.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.MedicalInvoice.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.MedicalService.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.NavSettings.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Company.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.ContactPerson.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Provider.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Provider.class.getName() + ".praxes");
            createCache(cm, hu.paninform.startmedsol.domain.Provider.class.getName() + ".employees");
            createCache(cm, hu.paninform.startmedsol.domain.ErrorRecord.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.SpecialistsAdvice.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.HashTag.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.HashTag.class.getName() + ".patients");
            createCache(cm, hu.paninform.startmedsol.domain.Statistics.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Announcement.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.FeedBackMessage.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.DataGenerator.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Prescription.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PrescriptionEesztId.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.EhrDocument.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.ExternalDocument.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.MedicalCaseDiagnosis.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PerformedProcedure.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.ProceduresOfPraxis.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.CaseTextDocumentation.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.CsDiagnosis.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.CsCountry.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.CsPostalCode.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.CodeSetLoad.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Address.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.CsProcedure.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.CsSenderOrganization.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.DictionaryItem.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.DictionaryItem.class.getName() + ".dictionaryTranslations");
            createCache(cm, hu.paninform.startmedsol.domain.DictionaryItem.class.getName() + ".parents");
            createCache(cm, hu.paninform.startmedsol.domain.DictionaryItem.class.getName() + ".children");
            createCache(cm, hu.paninform.startmedsol.domain.DictionaryTranslation.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Employee.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Employee.class.getName() + ".praxes");
            createCache(cm, hu.paninform.startmedsol.domain.Employee.class.getName() + ".providers");
            createCache(cm, hu.paninform.startmedsol.domain.BillingInformation.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.MedicalCase.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.MedicalCase.class.getName() + ".documents");
            createCache(cm, hu.paninform.startmedsol.domain.Patient.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Patient.class.getName() + ".cards");
            createCache(cm, hu.paninform.startmedsol.domain.Patient.class.getName() + ".tags");
            createCache(cm, hu.paninform.startmedsol.domain.PersonalData.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PersonalData.class.getName() + ".addresses");
            createCache(cm, hu.paninform.startmedsol.domain.Card.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Praxis.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphAidISOBook.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphAtcIndex.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphCompany.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphEuIndication.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphEuScore.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphEuScore.class.getName() + ".euIndications");
            createCache(cm, hu.paninform.startmedsol.domain.PphEuScore.class.getName() + ".qualificEuScoreLinks");
            createCache(cm, hu.paninform.startmedsol.domain.PphEuScore.class.getName() + ".medicines");
            createCache(cm, hu.paninform.startmedsol.domain.PphMedicine.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphMedicine.class.getName() + ".subSidies");
            createCache(cm, hu.paninform.startmedsol.domain.PphMedicine.class.getName() + ".euScores");
            createCache(cm, hu.paninform.startmedsol.domain.PphSubsidy.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphMedicineForm.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphMedicineQualifiedName.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphMedicineQualifiedName.class.getName() + ".medicines");
            createCache(cm, hu.paninform.startmedsol.domain.PphMotivationGroup.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphNiche.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphPuphaInstitution.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphPuphaVersion.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphQualification.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphQualificationDoctorAll.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphQualificEuScoreLink.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PphSpecBudget.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.PuphaLoader.class.getName());
            createCache(cm, hu.paninform.startmedsol.domain.Validity.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
