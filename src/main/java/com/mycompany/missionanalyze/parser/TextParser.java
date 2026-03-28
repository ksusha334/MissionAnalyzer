/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

import com.mycompany.missionanalyze.model.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author march
 */
public class TextParser implements MissionParser{
    
    @Override
    public Mission parse(File file) throws Exception {
        Mission mission = new Mission();
        List<Sorcerer> sorcerers = new ArrayList<>();
        List<Technique> techniques = new ArrayList<>();
        Curse curse = new Curse();
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
       
            String[] parts = line.split(":", 2);
            if (parts.length < 2) continue;
            
            String key = parts[0].trim();
            String value = parts[1].trim();
            
            if (key.equals("missionId")) mission.setMissionId(value);
            else if (key.equals("date")) mission.setDate(value);
            else if (key.equals("location")) mission.setLocation(value);
            else if (key.equals("outcome")) mission.setOutcome(value);
            else if (key.equals("damageCost")) mission.setDamageCost(Long.parseLong(value));
            
            else if (key.equals("curse.name")) curse.setName(value);
            else if (key.equals("curse.threatLevel")) curse.setThreatLevel(value);
            
            else if (key.startsWith("sorcerer[") && key.endsWith(".name")) {
                int index = Integer.parseInt(key.substring(9, key.indexOf("]"))); 
                while (sorcerers.size() <= index) sorcerers.add(new Sorcerer());
                sorcerers.get(index).setName(value);
            }
            else if (key.startsWith("sorcerer[") && key.endsWith(".rank")) {
                int index = Integer.parseInt(key.substring(9, key.indexOf("]")));
                while (sorcerers.size() <= index) sorcerers.add(new Sorcerer());
                sorcerers.get(index).setRank(value);
            }
            
            else if (key.startsWith("technique[") && key.endsWith(".name")) {
                int index = Integer.parseInt(key.substring(10, key.indexOf("]")));
                while (techniques.size() <= index) techniques.add(new Technique());
                techniques.get(index).setName(value);
            }
            else if (key.startsWith("technique[") && key.endsWith(".type")) {
                int index = Integer.parseInt(key.substring(10, key.indexOf("]")));
                while (techniques.size() <= index) techniques.add(new Technique());
                techniques.get(index).setType(value);
            }
            else if (key.startsWith("technique[") && key.endsWith(".owner")) {
                int index = Integer.parseInt(key.substring(10, key.indexOf("]")));
                while (techniques.size() <= index) techniques.add(new Technique());
                techniques.get(index).setOwner(value);
            }
            else if (key.startsWith("technique[") && key.endsWith(".damage")) {
                int index = Integer.parseInt(key.substring(10, key.indexOf("]")));
                while (techniques.size() <= index) techniques.add(new Technique());
                techniques.get(index).setDamage(Long.parseLong(value));
            }
            
            if (key.equals("note") || key.equals("comment")) mission.setComment(value);
            
        }
        
        reader.close();
        
        mission.setCurse(curse);
        mission.setSorcerers(sorcerers);
        mission.setTechniques(techniques);
        
        return mission;
    }
    
    @Override
    public boolean canParse(String fileName) {
        return fileName.toLowerCase().endsWith(".txt");
    }
}