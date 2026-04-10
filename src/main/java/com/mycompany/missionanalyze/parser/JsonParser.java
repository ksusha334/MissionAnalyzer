/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

import com.mycompany.missionanalyze.model.Mission;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.missionanalyze.builder.MissionBuilderImpl;
import java.io.File;

/**
 *
 * @author march
 */
public class JsonParser extends BaseParser {
    
    @Override
    protected Mission doParse(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Mission tempMission = mapper.readValue(file, Mission.class);
        return MissionBuilderImpl.fromMission(tempMission).build();
    }

    @Override
    public boolean canParse(String fileName) {
        return fileName.toLowerCase().endsWith(".json");  //перeводим в нижний регистр, чтобы точно избежать ошибок
    }
  
} 