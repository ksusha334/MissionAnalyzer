/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author march
 */
public class Mission {
    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private long damageCost;
    private String comment;
    private Curse curse;
    
    private List<Sorcerer> sorcerers;
    private List<Technique> techniques;
    
    private Map<String, Object> extensions = new HashMap<>();
    public String getMissionId() { 
        return missionId; 
    }
    public void setMissionId(String id) { 
        this.missionId = id; 
    }
    
    public String getDate() { 
        return date;
    }
    public void setDate(String date) { 
        this.date = date; 
    }
    
    public String getLocation() {
        return location; 
    }
    
    public void setLocation(String location) { 
        this.location = location; 
    }
    
    public String getOutcome() { 
        return outcome; 
    }
    
    public void setOutcome(String result) { 
        this.outcome = result; 
    }
    
    public long getDamageCost() {
        return damageCost;
    }
    
    public void setDamageCost(long damage) { 
        this.damageCost = damage; 
    }
    
    public Curse getCurse() {
        return curse;
    }
    
    public void setCurse(Curse curse) { 
        this.curse = curse; 
    }
    
    public List<Sorcerer> getSorcerers() { 
        return sorcerers; 
    }
    
    public void setSorcerers(List<Sorcerer> sorcerers) {
        this.sorcerers = sorcerers; 
    }
    
    public List<Technique> getTechniques() {
        return techniques; 
    }
    
    public void setTechniques(List<Technique> techniques) {
        this.techniques = techniques; 
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getComment() {
        return comment;
    }
    
    public Map<String, Object> getExtensions() {
        return extensions;
    }
    
    @JsonAnySetter
    public void addExtension(String key, Object value) {
        this.extensions.put(key, value);
    }
   
    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = extensions;
    }
}
