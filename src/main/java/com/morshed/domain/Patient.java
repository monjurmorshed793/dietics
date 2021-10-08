package com.morshed.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.morshed.domain.enumeration.GainLossType;
import com.morshed.domain.enumeration.Gender;
import com.morshed.domain.enumeration.HeightMeasureType;
import com.morshed.domain.enumeration.WeightType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Patient.
 */
@Document(collection = "patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("address")
    private String address;

    @Field("hospital")
    private String hospital;

    @Field("admission_date")
    private LocalDate admissionDate;

    @Field("reason_of_admission")
    private String reasonOfAdmission;

    @Field("word_no")
    private String wordNo;

    @Field("bed_no")
    private String bedNo;

    @Field("health_condition")
    private String healthCondition;

    @Field("mental_status")
    private String mentalStatus;

    @Field("age")
    private Integer age;

    @Field("sex")
    private Gender sex;

    @Field("weight")
    private Double weight;

    @Field("weight_type")
    private WeightType weightType;

    @Field("height")
    private Double height;

    @Field("height_measure_type")
    private HeightMeasureType heightMeasureType;

    @Field("ibw")
    private Double ibw;

    @Field("bmi")
    private Double bmi;

    @Field("recent_weight_gain_loss")
    private Boolean recentWeightGainLoss;

    @Field("gain_loss_measure")
    private Double gainLossMeasure;

    @Field("gain_loss_time_frame")
    private Double gainLossTimeFrame;

    @Field("gain_loss_type")
    private GainLossType gainLossType;

    @DBRef
    @Field("nutritionState")
    private NutritionState nutritionState;

    @DBRef
    @Field("activityLevel")
    private ActivityLevel activityLevel;

    @DBRef
    @Field("dietNatures")
    @JsonIgnoreProperties(value = { "patients" }, allowSetters = true)
    private Set<DietNature> dietNatures = new HashSet<>();

    @DBRef
    @Field("supplements")
    @JsonIgnoreProperties(value = { "patients" }, allowSetters = true)
    private Set<Supplements> supplements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Patient id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Patient name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public Patient address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHospital() {
        return this.hospital;
    }

    public Patient hospital(String hospital) {
        this.setHospital(hospital);
        return this;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public LocalDate getAdmissionDate() {
        return this.admissionDate;
    }

    public Patient admissionDate(LocalDate admissionDate) {
        this.setAdmissionDate(admissionDate);
        return this;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getReasonOfAdmission() {
        return this.reasonOfAdmission;
    }

    public Patient reasonOfAdmission(String reasonOfAdmission) {
        this.setReasonOfAdmission(reasonOfAdmission);
        return this;
    }

    public void setReasonOfAdmission(String reasonOfAdmission) {
        this.reasonOfAdmission = reasonOfAdmission;
    }

    public String getWordNo() {
        return this.wordNo;
    }

    public Patient wordNo(String wordNo) {
        this.setWordNo(wordNo);
        return this;
    }

    public void setWordNo(String wordNo) {
        this.wordNo = wordNo;
    }

    public String getBedNo() {
        return this.bedNo;
    }

    public Patient bedNo(String bedNo) {
        this.setBedNo(bedNo);
        return this;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getHealthCondition() {
        return this.healthCondition;
    }

    public Patient healthCondition(String healthCondition) {
        this.setHealthCondition(healthCondition);
        return this;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getMentalStatus() {
        return this.mentalStatus;
    }

    public Patient mentalStatus(String mentalStatus) {
        this.setMentalStatus(mentalStatus);
        return this;
    }

    public void setMentalStatus(String mentalStatus) {
        this.mentalStatus = mentalStatus;
    }

    public Integer getAge() {
        return this.age;
    }

    public Patient age(Integer age) {
        this.setAge(age);
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getSex() {
        return this.sex;
    }

    public Patient sex(Gender sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Double getWeight() {
        return this.weight;
    }

    public Patient weight(Double weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public WeightType getWeightType() {
        return this.weightType;
    }

    public Patient weightType(WeightType weightType) {
        this.setWeightType(weightType);
        return this;
    }

    public void setWeightType(WeightType weightType) {
        this.weightType = weightType;
    }

    public Double getHeight() {
        return this.height;
    }

    public Patient height(Double height) {
        this.setHeight(height);
        return this;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public HeightMeasureType getHeightMeasureType() {
        return this.heightMeasureType;
    }

    public Patient heightMeasureType(HeightMeasureType heightMeasureType) {
        this.setHeightMeasureType(heightMeasureType);
        return this;
    }

    public void setHeightMeasureType(HeightMeasureType heightMeasureType) {
        this.heightMeasureType = heightMeasureType;
    }

    public Double getIbw() {
        return this.ibw;
    }

    public Patient ibw(Double ibw) {
        this.setIbw(ibw);
        return this;
    }

    public void setIbw(Double ibw) {
        this.ibw = ibw;
    }

    public Double getBmi() {
        return this.bmi;
    }

    public Patient bmi(Double bmi) {
        this.setBmi(bmi);
        return this;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Boolean getRecentWeightGainLoss() {
        return this.recentWeightGainLoss;
    }

    public Patient recentWeightGainLoss(Boolean recentWeightGainLoss) {
        this.setRecentWeightGainLoss(recentWeightGainLoss);
        return this;
    }

    public void setRecentWeightGainLoss(Boolean recentWeightGainLoss) {
        this.recentWeightGainLoss = recentWeightGainLoss;
    }

    public Double getGainLossMeasure() {
        return this.gainLossMeasure;
    }

    public Patient gainLossMeasure(Double gainLossMeasure) {
        this.setGainLossMeasure(gainLossMeasure);
        return this;
    }

    public void setGainLossMeasure(Double gainLossMeasure) {
        this.gainLossMeasure = gainLossMeasure;
    }

    public Double getGainLossTimeFrame() {
        return this.gainLossTimeFrame;
    }

    public Patient gainLossTimeFrame(Double gainLossTimeFrame) {
        this.setGainLossTimeFrame(gainLossTimeFrame);
        return this;
    }

    public void setGainLossTimeFrame(Double gainLossTimeFrame) {
        this.gainLossTimeFrame = gainLossTimeFrame;
    }

    public GainLossType getGainLossType() {
        return this.gainLossType;
    }

    public Patient gainLossType(GainLossType gainLossType) {
        this.setGainLossType(gainLossType);
        return this;
    }

    public void setGainLossType(GainLossType gainLossType) {
        this.gainLossType = gainLossType;
    }

    public NutritionState getNutritionState() {
        return this.nutritionState;
    }

    public void setNutritionState(NutritionState nutritionState) {
        this.nutritionState = nutritionState;
    }

    public Patient nutritionState(NutritionState nutritionState) {
        this.setNutritionState(nutritionState);
        return this;
    }

    public ActivityLevel getActivityLevel() {
        return this.activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Patient activityLevel(ActivityLevel activityLevel) {
        this.setActivityLevel(activityLevel);
        return this;
    }

    public Set<DietNature> getDietNatures() {
        return this.dietNatures;
    }

    public void setDietNatures(Set<DietNature> dietNatures) {
        this.dietNatures = dietNatures;
    }

    public Patient dietNatures(Set<DietNature> dietNatures) {
        this.setDietNatures(dietNatures);
        return this;
    }

    public Patient addDietNatures(DietNature dietNature) {
        this.dietNatures.add(dietNature);
        dietNature.getPatients().add(this);
        return this;
    }

    public Patient removeDietNatures(DietNature dietNature) {
        this.dietNatures.remove(dietNature);
        dietNature.getPatients().remove(this);
        return this;
    }

    public Set<Supplements> getSupplements() {
        return this.supplements;
    }

    public void setSupplements(Set<Supplements> supplements) {
        this.supplements = supplements;
    }

    public Patient supplements(Set<Supplements> supplements) {
        this.setSupplements(supplements);
        return this;
    }

    public Patient addSupplements(Supplements supplements) {
        this.supplements.add(supplements);
        supplements.getPatients().add(this);
        return this;
    }

    public Patient removeSupplements(Supplements supplements) {
        this.supplements.remove(supplements);
        supplements.getPatients().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", hospital='" + getHospital() + "'" +
            ", admissionDate='" + getAdmissionDate() + "'" +
            ", reasonOfAdmission='" + getReasonOfAdmission() + "'" +
            ", wordNo='" + getWordNo() + "'" +
            ", bedNo='" + getBedNo() + "'" +
            ", healthCondition='" + getHealthCondition() + "'" +
            ", mentalStatus='" + getMentalStatus() + "'" +
            ", age=" + getAge() +
            ", sex='" + getSex() + "'" +
            ", weight=" + getWeight() +
            ", weightType='" + getWeightType() + "'" +
            ", height=" + getHeight() +
            ", heightMeasureType='" + getHeightMeasureType() + "'" +
            ", ibw=" + getIbw() +
            ", bmi=" + getBmi() +
            ", recentWeightGainLoss='" + getRecentWeightGainLoss() + "'" +
            ", gainLossMeasure=" + getGainLossMeasure() +
            ", gainLossTimeFrame=" + getGainLossTimeFrame() +
            ", gainLossType='" + getGainLossType() + "'" +
            "}";
    }
}
