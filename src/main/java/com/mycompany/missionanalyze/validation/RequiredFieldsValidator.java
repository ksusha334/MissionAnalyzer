/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.validation;

import com.mycompany.missionanalyze.model.Curse;
import com.mycompany.missionanalyze.model.Mission;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author march
 */
public class RequiredFieldsValidator implements Validator {
    @Override
    public List<String> validate(Mission mission) {
        List<String> errors = new ArrayList<String>();
        
        if (mission.getMissionId() == null || mission.getMissionId().trim().isEmpty()) {
            errors.add("Ошибка: missionId - обязательное поле");
        }

        if (mission.getDate() == null || mission.getDate().trim().isEmpty()) {
            errors.add("Ошибка: date - обязательное поле");
        }
        
        if (mission.getLocation() == null || mission.getLocation().trim().isEmpty()) {
            errors.add("Ошибка: location - обязательное поле");
        }
        
        if (mission.getOutcome() == null || mission.getOutcome().trim().isEmpty()) {
            errors.add("Ошибка: outcome - обязательное поле");
        }
        
        Curse curse = mission.getCurse();
        if (curse == null) {
            errors.add("Ошибка: curse - обязательный блок");
        } else {
            if (curse.getName() == null || curse.getName().trim().isEmpty()) {
                errors.add("Ошибка: curse.name - обязательное поле");
            }
            if (curse.getThreatLevel() == null || curse.getThreatLevel().trim().isEmpty()) {
                errors.add("Ошибка: curse.threatLevel - обязательное поле");
            }
        }
        
        return errors;
    }
    
}
