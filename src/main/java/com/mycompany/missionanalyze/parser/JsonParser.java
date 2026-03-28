/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

import com.mycompany.missionanalyze.model.Mission;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

/**
 *
 * @author march
 */
public class JsonParser implements MissionParser{
    
     @Override
    public Mission parse(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, Mission.class);
    }
    
    @Override
    public boolean canParse(String fileName) {
        return fileName.toLowerCase().endsWith(".json");  //перeводим в нижний регистр, чтобы точно избежать ошибок
    }
  
} 