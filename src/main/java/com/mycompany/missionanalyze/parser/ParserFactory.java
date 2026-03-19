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
public class ParserFactory {
    private List<MissionParser> parsers;
    
    public ParserFactory() {
        parsers = new ArrayList<>();
        parsers.add(new JsonParser());
        parsers.add(new XmlParser());
        parsers.add(new TextParser());
    }
    
    public MissionParser getParser(String fileName) {
        for (MissionParser parser : parsers) {
            if (parser.canParse(fileName)) {
                return parser;
            }
        }
        throw new RuntimeException("Формат файла не поддерживается: " + fileName);
    }
}