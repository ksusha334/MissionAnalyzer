/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

/**
 *
 * @author march
 */
import com.mycompany.missionanalyze.model.Mission;
import java.io.File;

public interface MissionParser {
    Mission parse(File file) throws Exception; 
    boolean canParse(String fileName);          
}