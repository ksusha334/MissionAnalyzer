/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

//import com.mycompany.missionanalyze.model.*;
//import javax.xml.parsers.*;
//import org.w3c.dom.*;
//import java.io.File;
//import java.util.*;

import com.mycompany.missionanalyze.model.Mission;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 *
 * @author march
 */
public class XmlParser implements MissionParser{
    @Override
    public Mission parse(File file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Mission.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Mission) unmarshaller.unmarshal(file);
    }
    
    @Override
    public boolean canParse(String fileName) {
        return fileName.toLowerCase().endsWith(".xml");
    }
}