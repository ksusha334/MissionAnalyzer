/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.missionanalyze.model.Mission;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.mycompany.missionanalyze.builder.MissionBuilderImpl;
import java.io.File;

/**
 *
 * @author march
 */
public class YamlParser extends BaseParser {
    
    private ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    
    @Override
    protected Mission doParse(File file) throws Exception {
        Mission tempMission = objectMapper.readValue(file, Mission.class);
        return MissionBuilderImpl.fromMission(tempMission).build();
    }
    
    @Override
    public boolean canParse(String fileName) {
        String lower = fileName.toLowerCase();
        return lower.endsWith(".yaml") || lower.endsWith(".yml");
    }
    
}
