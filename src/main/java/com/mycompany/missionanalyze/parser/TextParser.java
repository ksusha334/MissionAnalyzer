/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

import com.mycompany.missionanalyze.builder.MissionBuilder;
import com.mycompany.missionanalyze.builder.MissionBuilderImpl;
import com.mycompany.missionanalyze.model.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author march
 */
public class TextParser extends BaseParser {
    
    @Override
    public Mission doParse(File file) throws Exception {
        MissionBuilder builder = new MissionBuilderImpl();
        
        List<Sorcerer> sorcerers = new ArrayList<Sorcerer>();
        List<Technique> techniques = new ArrayList<Technique>();
        
        String currentSection = null;
        List<String> sectionLines = new ArrayList<String>();
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            
            if (line.startsWith("[") && line.endsWith("]")) {
                if (currentSection != null) {
                    processSection(currentSection, sectionLines, builder, sorcerers, techniques);
                }
                currentSection = line.substring(1, line.length() - 1);
                sectionLines.clear();
            }
            else if (currentSection == null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    applyMainField(parts[0].trim(), parts[1].trim(), builder);
                }
            }
            else {
                sectionLines.add(line);
            }
        }
        
        if (currentSection != null) {
            processSection(currentSection, sectionLines, builder, sorcerers, techniques);
        }
        
        reader.close();
        
        for (Sorcerer s : sorcerers) {
            builder.addSorcerer(s);
        }
        for (Technique t : techniques) {
            builder.addTechnique(t);
        }
        
        return builder.build();
    }
    
    private void processSection(String sectionName, List<String> lines, MissionBuilder builder,List<Sorcerer> sorcerers, List<Technique> techniques) {
        if (sectionName.equals("MISSION")) {
            for (String line : lines) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    applyMainField(key, value, builder);
                }
            }
        }
        else if (sectionName.equals("CURSE")) {
            String name = null;
            String threatLevel = null;
            for (String line : lines) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if (key.equals("name")) {
                        name = value;
                    } else if (key.equals("threatLevel")) {
                        threatLevel = value;
                    }
                }
            }
            if (name != null || threatLevel != null) {
                Curse curse = new Curse();
                curse.setName(name);
                curse.setThreatLevel(threatLevel);
                builder.setCurse(curse);
            }
        }
        else if (sectionName.equals("SORCERER")) {
            Sorcerer sorcerer = new Sorcerer();
            for (String line : lines) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if (key.equals("name")) {
                        sorcerer.setName(value);
                    } else if (key.equals("rank")) {
                        sorcerer.setRank(value);
                    } else if (key.equals("age")) {
                        try {
                            sorcerer.setAge(Integer.parseInt(value));
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
            sorcerers.add(sorcerer);
        }
        else if (sectionName.equals("TECHNIQUE")) {
            Technique technique = new Technique();
            for (String line : lines) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if (key.equals("name")) {
                        technique.setName(value);
                    } else if (key.equals("type")) {
                        technique.setType(value);
                    } else if (key.equals("owner")) {
                        technique.setOwner(value);
                    } else if (key.equals("damage")) {
                        try {
                            technique.setDamage(Long.parseLong(value));
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
            techniques.add(technique);
        }
        else {
            StringBuilder sectionContent = new StringBuilder();
            for (String line : lines) {
                sectionContent.append(line).append("\n");
            }
            builder.addExtension("section_" + sectionName, sectionContent.toString());
        }
    }
    
    private void applyMainField(String key, String value, MissionBuilder builder) {
        if (key.equals("missionId")) {
            builder.setMissionId(value);
        } else if (key.equals("date")) {
            builder.setDate(value);
        } else if (key.equals("location")) {
            builder.setLocation(value);
        } else if (key.equals("outcome")) {
            builder.setOutcome(value);
        } else if (key.equals("damageCost")) {
            try {
                builder.setDamageCost(Long.parseLong(value));
            } catch (NumberFormatException e) {
            }
        } else if (key.equals("comment") || key.equals("note")) {
            builder.setComment(value);
        } else {
            builder.addExtension(key, value);
        }
    }
    
    @Override
    public boolean canParse(String fileName) {
        return fileName.toLowerCase().endsWith(".txt");
    }
}