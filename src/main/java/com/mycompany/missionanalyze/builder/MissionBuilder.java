/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.missionanalyze.builder;

import com.mycompany.missionanalyze.model.Curse;
import com.mycompany.missionanalyze.model.Mission;
import com.mycompany.missionanalyze.model.Sorcerer;
import com.mycompany.missionanalyze.model.Technique;

/**
 *
 * @author march
 */
public interface MissionBuilder {
    MissionBuilder reset(); //сбрасывает все значения, начинает сборку заново 
    MissionBuilder setMissionId(String id);
    MissionBuilder setDate(String date);
    MissionBuilder setLocation(String location);
    MissionBuilder setOutcome(String outcome);
    MissionBuilder setDamageCost(long cost);
    MissionBuilder setComment(String comment);
    MissionBuilder setCurse(Curse curse);
    MissionBuilder addSorcerer(Sorcerer sorcerer);
    MissionBuilder addTechnique(Technique technique);
    MissionBuilder addExtension(String key, Object value);
    Mission build();  
}
