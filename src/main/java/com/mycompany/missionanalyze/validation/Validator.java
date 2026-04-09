/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.missionanalyze.validation;

import com.mycompany.missionanalyze.model.Mission;
import java.util.List;

/**
 *
 * @author march
 */
public interface Validator {
    List<String> validate(Mission mission);
}
