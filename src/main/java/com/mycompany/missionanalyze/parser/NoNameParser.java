/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.missionanalyze.parser;

import com.mycompany.missionanalyze.builder.MissionBuilder;
import com.mycompany.missionanalyze.builder.MissionBuilderImpl;
import com.mycompany.missionanalyze.model.Mission;
import com.mycompany.missionanalyze.model.Curse;
import com.mycompany.missionanalyze.model.Sorcerer;
import com.mycompany.missionanalyze.model.Technique;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author march
 */
public class NoNameParser extends BaseParser {
    
    @Override
    public Mission doParse(File file) throws Exception {
                MissionBuilder builder = new MissionBuilderImpl();
        
        List<Sorcerer> sorcerers = new ArrayList<>();
        List<Technique> techniques = new ArrayList<>();

        int unknownCounter = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split("\\|");
                if (parts.length == 0) continue;
                
                String command = parts[0];
                if (command.equals("MISSION_CREATED")) {
                    if (parts.length >= 4) {
                        builder.setMissionId(parts[1]);
                        builder.setDate(parts[2]);
                        builder.setLocation(parts[3]);
                    }
                }
                else if (command.equals("CURSE_DETECTED")) {
                    if (parts.length >= 3) {
                        Curse curse = new Curse();
                        curse.setName(parts[1]);
                        curse.setThreatLevel(parts[2]);
                        builder.setCurse(curse);
                    }
                }
                else if (command.equals("SORCERER_ASSIGNED")) {
                    if (parts.length >= 3) {
                        Sorcerer sorcerer = new Sorcerer();
                        sorcerer.setName(parts[1]);
                        sorcerer.setRank(parts[2]);
                        sorcerers.add(sorcerer);
                    }
                }
                else if (command.equals("TECHNIQUE_USED")) {
                    if (parts.length >= 5) {
                        Technique technique = new Technique();
                        technique.setName(parts[1]);
                        technique.setType(parts[2]);
                        technique.setOwner(parts[3]);
                        try {
                            technique.setDamage(Long.parseLong(parts[4]));
                        } catch (NumberFormatException e) {
                            technique.setDamage(0);
                        }
                        techniques.add(technique);
                    }
                }
                else if (command.equals("MISSION_RESULT")) {
                    if (parts.length >= 3) {
                        builder.setOutcome(parts[1]);
                        try {
                            String damageStr = parts[2].replace("damageCost=", "");
                            builder.setDamageCost(Long.parseLong(damageStr));
                        } catch (NumberFormatException e) {
                            builder.setDamageCost(0);
                        }
                    }
                }
                else {
                    StringBuilder valueBuilder = new StringBuilder();
                    for (int i = 1; i < parts.length; i++) {
                        if (i > 1) valueBuilder.append("|");
                        valueBuilder.append(parts[i]);
                    }
                    builder.addExtension(unknownCounter + "_" + command, valueBuilder.toString());
                    unknownCounter++;
                }
            }
        }
        
        for (Sorcerer s : sorcerers) {
            builder.addSorcerer(s);
        }
        for (Technique t : techniques) {
            builder.addTechnique(t);
        }
        
        return builder.build();
    }
    
    @Override
    public boolean canParse(String fileName) {
        String lowerName = fileName.toLowerCase();
        if (lowerName.endsWith(".json") || 
            lowerName.endsWith(".xml") || 
            lowerName.endsWith(".txt") || 
            lowerName.endsWith(".yaml") || 
            lowerName.endsWith(".yml")) {
            return false;
        }
        return true;
    }
}

