/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.builder;

import com.mycompany.missionanalyze.model.Curse;
import com.mycompany.missionanalyze.model.Mission;
import com.mycompany.missionanalyze.model.Sorcerer;
import com.mycompany.missionanalyze.model.Technique;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author march
 */
public class MissionBuilderImpl implements MissionBuilder {
    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private long damageCost;
    private String comment;
    private Curse curse;
    private List<Sorcerer> sorcerers;
    private List<Technique> techniques;
    private Map<String, Object> extensions;
    
    public MissionBuilderImpl() {
        reset();
    }
    
    @Override
    public MissionBuilder reset() {
        this.missionId = null;
        this.date = null;
        this.location = null;
        this.outcome = null;
        this.damageCost = 0;
        this.comment = null;
        this.curse = null;
        this.sorcerers = new ArrayList<Sorcerer>();
        this.techniques = new ArrayList<Technique>();
        this.extensions = new HashMap<String, Object>();
        return this;
    }
    
    @Override
    public MissionBuilder setMissionId(String id) {
        this.missionId = id;
        return this;
    }
    
    @Override
    public MissionBuilder setDate(String date) {
        this.date = date;
        return this;
    }
    
    @Override
    public MissionBuilder setLocation(String location) {
        this.location = location;
        return this;
    }
    
    @Override
    public MissionBuilder setOutcome(String outcome) {
        this.outcome = outcome;
        return this;
    }
    
    @Override
    public MissionBuilder setDamageCost(long cost) {
        this.damageCost = cost;
        return this;
    }
    
    @Override
    public MissionBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }
    
    @Override
    public MissionBuilder setCurse(Curse curse) {
        this.curse = curse;
        return this;
    }
    
    @Override
    public MissionBuilder addSorcerer(Sorcerer sorcerer) {
        if (sorcerer != null) {
            this.sorcerers.add(sorcerer);
        }
        return this;
    }
    
    @Override
    public MissionBuilder addTechnique(Technique technique) {
        if (technique != null) {
            this.techniques.add(technique);
        }
        return this;
    }
    
    @Override
    public MissionBuilder addExtension(String key, Object value) {
        if (key != null && value != null) {
            this.extensions.put(key, value);
        }
        return this;
    }
    
    @Override
    public Mission build() {
        Mission mission = new Mission();
        
        mission.setMissionId(missionId);
        mission.setDate(date);
        mission.setLocation(location);
        mission.setOutcome(outcome);
        mission.setDamageCost(damageCost);
        mission.setComment(comment);
        mission.setCurse(curse);
        
        if (sorcerers != null && !sorcerers.isEmpty()) {
            mission.setSorcerers(sorcerers);
        }
        
        if (techniques != null && !techniques.isEmpty()) {
            mission.setTechniques(techniques);
        }
        
        for (Map.Entry<String, Object> entry : extensions.entrySet()) { // все расширенные поля
            mission.addExtension(entry.getKey(), entry.getValue());
        }
        
        return mission;
    }
    
    public static MissionBuilder fromMission(Mission mission) {
        MissionBuilder builder = new MissionBuilderImpl();
        builder.setMissionId(mission.getMissionId());
        builder.setDate(mission.getDate());
        builder.setLocation(mission.getLocation());
        builder.setOutcome(mission.getOutcome());
        builder.setDamageCost(mission.getDamageCost());
        builder.setComment(mission.getComment());
        builder.setCurse(mission.getCurse());

        if (mission.getSorcerers() != null) {
            for (Sorcerer s : mission.getSorcerers()) {
                builder.addSorcerer(s);
            }
        }

        if (mission.getTechniques() != null) {
            for (Technique t : mission.getTechniques()) {
                builder.addTechnique(t);
            }
        }

        if (mission.getExtensions() != null) {
            for (Map.Entry<String, Object> entry : mission.getExtensions().entrySet()) {
                builder.addExtension(entry.getKey(), entry.getValue());
            }
        }

        return builder;
    }
    
}
