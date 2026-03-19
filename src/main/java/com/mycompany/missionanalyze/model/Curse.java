/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.model;

/**
 *
 * @author march
 */
public class Curse {
    private String name;
    private String threatLevel;
    
    public Curse() {}
    
    public Curse(String name, String level) {
        this.name = name;
        this.threatLevel = level;
    }
    
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getThreatLevel() { 
        return threatLevel; 
    }
    
    public void setThreatLevel(String level) { 
        this.threatLevel = level; 
    } // ко
}