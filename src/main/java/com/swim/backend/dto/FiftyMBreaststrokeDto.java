package com.swim.backend.dto;

import java.time.LocalTime;

import org.springframework.beans.BeanUtils;

import com.swim.backend.model.FiftyMBreaststroke;

public class FiftyMBreaststrokeDto {
    
    private String id;

    private AthleteDto athleteId;

    private String gender;

    private Integer age;

    private String district;

    private LocalTime time;

    public FiftyMBreaststrokeDto() {
        setAthleteId(new AthleteDto());
    }

    public FiftyMBreaststrokeDto(FiftyMBreaststroke fiftyMBreaststroke) {
        BeanUtils.copyProperties(fiftyMBreaststroke, this);
        if (fiftyMBreaststroke.getAthleteId() != null) {
            setAthleteId(new AthleteDto(fiftyMBreaststroke.getAthleteId()));
        }
    }

    public FiftyMBreaststroke toModel() {
        FiftyMBreaststroke model = new FiftyMBreaststroke();
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "FiftyMBreaststrokeDto [id=" + id + ", athleteId=" + athleteId + ", gender=" + gender + ", age=" + age
                + ", district=" + district + ", time=" + time + "]";
    }

}
