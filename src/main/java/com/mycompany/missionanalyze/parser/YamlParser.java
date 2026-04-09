/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.missionanalyze.model.Mission;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;

/**
 *
 * @author march
 */
public class YamlParser extends BaseParser {
    
    @Override
    public Mission doParse(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(file, Mission.class);
    }
    
    @Override
    public boolean canParse(String fileName) {
        String lower = fileName.toLowerCase();
        return lower.endsWith(".yaml") || lower.endsWith(".yml");
    }
    
}
