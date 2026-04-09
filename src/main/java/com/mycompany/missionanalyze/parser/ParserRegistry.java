/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author march
 */
public class ParserRegistry {
    private List<MissionParser> parsers;
    
    public ParserRegistry() {
        this.parsers = new ArrayList<MissionParser>();
    }
    
    public void register(MissionParser parser) {
        parsers.add(parser);
    }
    
    public MissionParser getParser(String fileName) {
        for (MissionParser parser : parsers) {
            if (parser.canParse(fileName)) {
                return parser;
            }
        }
        throw new RuntimeException("Формат файла не поддерживается: " + fileName);
    }
    
    public List<MissionParser> getRegisteredParsers() {
        return new ArrayList<MissionParser>(parsers);
    }
}