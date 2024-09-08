package com.swim.backend.dto;

import org.springframework.beans.BeanUtils;

import com.swim.backend.model.Athlete;

public class AthleteDto {
    
    private Integer athleteId;

    private String engName;

    private String chName;

    private String gender;

    private Integer age;

    private String district;

    private Integer phone;

    private Boolean fiftyMFreestyle;

    private Boolean fiftyMBackstroke;

    private Boolean fiftyMBreaststroke;

    private Boolean fiftyMButterflystroke;

    private Boolean hundredMFreestyle;

    private Boolean hundredMBreaststroke;

    private Boolean fiftyMKickBoard;

    public AthleteDto() {

    }

    public AthleteDto(Athlete athlete) {
        BeanUtils.copyProperties(athlete, this);
    }

    public Athlete toModel() {
        Athlete model = new Athlete();
        BeanUtils.copyProperties(this, model);
        return model;
    }

    public Integer getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Integer athleteId) {
        this.athleteId = athleteId;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Boolean getFiftyMFreestyle() {
        return fiftyMFreestyle;
    }

    public void setFiftyMFreestyle(Boolean fiftyMFreestyle) {
        this.fiftyMFreestyle = fiftyMFreestyle;
    }

    public Boolean getFiftyMBackstroke() {
        return fiftyMBackstroke;
    }

    public void setFiftyMBackstroke(Boolean fiftyMBackstroke) {
        this.fiftyMBackstroke = fiftyMBackstroke;
    }

    public Boolean getFiftyMBreaststroke() {
        return fiftyMBreaststroke;
    }

    public void setFiftyMBreaststroke(Boolean fiftyMBreaststroke) {
        this.fiftyMBreaststroke = fiftyMBreaststroke;
    }

    public Boolean getFiftyMButterflystroke() {
        return fiftyMButterflystroke;
    }

    public void setFiftyMButterflystroke(Boolean fiftyMButterflystroke) {
        this.fiftyMButterflystroke = fiftyMButterflystroke;
    }

    public Boolean getHundredMFreestyle() {
        return hundredMFreestyle;
    }

    public void setHundredMFreestyle(Boolean hundredMFreestyle) {
        this.hundredMFreestyle = hundredMFreestyle;
    }

    public Boolean getHundredMBreaststroke() {
        return hundredMBreaststroke;
    }

    public void setHundredMBreaststroke(Boolean hundredMBreaststroke) {
        this.hundredMBreaststroke = hundredMBreaststroke;
    }

    public Boolean getFiftyMKickBoard() {
        return fiftyMKickBoard;
    }

    public void setFiftyMKickBoard(Boolean fiftyMKickBoard) {
        this.fiftyMKickBoard = fiftyMKickBoard;
    }

    @Override
    public String toString() {
        return "AthleteDto [athleteId=" + athleteId + ", engName=" + engName + ", chName=" + chName + ", gender="
                + gender + ", age=" + age + ", district=" + district + ", phone=" + phone + ", fiftyMFreestyle="
                + fiftyMFreestyle + ", fiftyMBackstroke=" + fiftyMBackstroke + ", fiftyMBreaststroke="
                + fiftyMBreaststroke + ", fiftyMButterflystroke=" + fiftyMButterflystroke + ", hundredMFreestyle="
                + hundredMFreestyle + ", hundredMBreaststroke=" + hundredMBreaststroke + ", fiftyMKickBoard="
                + fiftyMKickBoard + "]";
    }

}
