/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 *
 * @author march
 */
@XmlRootElement(name = "mission")
@XmlAccessorType(XmlAccessType.FIELD)
public class Mission {
    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private long damageCost;
    private Curse curse;
    
    @XmlElementWrapper(name = "sorcerers")
    @XmlElement(name = "sorcerer")
    private List<Sorcerer> sorcerers;
    
    @XmlElementWrapper(name = "techniques")
    @XmlElement(name = "technique")
    private List<Technique> techniques;
    private String comment;
    
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n   МИССИЯ \n");
        sb.append("ID: ").append(missionId).append("\n");
        sb.append("Дата: ").append(date).append("\n");
        sb.append("Место: ").append(location).append("\n");
        sb.append("Результат: ").append(outcome).append("\n");
        sb.append("Ущерб: ").append(damageCost).append("\n");
        
        if (curse != null) {
            sb.append("\n   ПРОКЛЯТИЕ \n");
            sb.append("Название: ").append(curse.getName()).append("\n");
            sb.append("Уровень: ").append(curse.getThreatLevel()).append("\n");
        }
        
        if (sorcerers != null && !sorcerers.isEmpty()) {
            sb.append("\n   МАГИ \n");
            for (Sorcerer s : sorcerers) {
                sb.append("• ").append(s.getName()).append(" (").append(s.getRank()).append(")\n").append(s.getAge()).append(")\n");
            }
        }
        
        if (techniques != null && !techniques.isEmpty()) {
            sb.append("\n   ТЕХНИКИ \n");
            for (Technique t : techniques) {
                sb.append("• ").append(t.getName()).append(" - ").append(t.getOwner())
                  .append(" (урон: ").append(t.getDamage()).append(")\n");
            }
        }
        
        sb.append("\nКомментарий: ");
        if (comment != null && !comment.trim().isEmpty()) {
        sb.append(comment).append("\n");
    } else {
        sb.append("отсутствует\n");
    }
        return sb.toString();
    }
}
