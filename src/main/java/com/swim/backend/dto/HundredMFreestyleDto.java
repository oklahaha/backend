package com.swim.backend.dto;

import org.springframework.beans.BeanUtils;

import com.swim.backend.model.HundredMFreestyle;

public class HundredMFreestyleDto {
    
    private String id;

    private AthleteDto athleteId;

    private String gender;

    private Integer age;

    private String district;

    private String time;

    public HundredMFreestyleDto() {
        setAthleteId(new AthleteDto());
    }

    public HundredMFreestyleDto(HundredMFreestyle hundredMFreestyle) {
        BeanUtils.copyProperties(hundredMFreestyle, this);
        if (hundredMFreestyle.getAthleteId() != null) {
            setAthleteId(new AthleteDto(hundredMFreestyle.getAthleteId()));
        }
    }

    public HundredMFreestyle toModel() {
        HundredMFreestyle model = new HundredMFreestyle();
		BeanUtils.copyProperties(this, model);
		return model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AthleteDto getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(AthleteDto athleteId) {
        this.athleteId = athleteId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HundredMFreestyleDto [id=" + id + ", athleteId=" + athleteId + ", gender=" + gender + ", age=" + age
                + ", district=" + district + ", time=" + time + "]";
    }

}
