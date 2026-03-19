/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

import com.mycompany.missionanalyze.model.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;
import java.util.*;

/**
 *
 * @author march
 */
public class XmlParser implements MissionParser{
    @Override
    public Mission parse(File file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        
        Mission mission = new Mission();
        
        // Основные поля
        mission.setMissionId(getTagValue("missionId", doc));
        mission.setDate(getTagValue("date", doc));
        mission.setLocation(getTagValue("location", doc));
        mission.setOutcome(getTagValue("outcome", doc));
        mission.setDamageCost(Long.parseLong(getTagValue("damageCost", doc)));
        
        // Проклятие
        Node curseNode = doc.getElementsByTagName("curse").item(0);
        if (curseNode != null) {
            Curse curse = new Curse();
            curse.setName(getChildTagValue("name", curseNode));
            curse.setThreatLevel(getChildTagValue("threatLevel", curseNode));
            mission.setCurse(curse);
        }
        
        // Сорсеры
        List<Sorcerer> sorcerers = new ArrayList<>();
        NodeList sorcererNodes = doc.getElementsByTagName("sorcerer");
        for (int i = 0; i < sorcererNodes.getLength(); i++) {
            Node node = sorcererNodes.item(i);
            Sorcerer s = new Sorcerer();
            s.setName(getChildTagValue("name", node));
            s.setRank(getChildTagValue("rank", node));
            sorcerers.add(s);
        }
        mission.setSorcerers(sorcerers);
        
        // Техники
        List<Technique> techniques = new ArrayList<>();
        NodeList techniqueNodes = doc.getElementsByTagName("technique");
        for (int i = 0; i < techniqueNodes.getLength(); i++) {
            Node node = techniqueNodes.item(i);
            Technique t = new Technique();
            t.setName(getChildTagValue("name", node));
            t.setType(getChildTagValue("type", node));
            t.setOwner(getChildTagValue("owner", node));
            t.setDamage(Long.parseLong(getChildTagValue("damage", node)));
            techniques.add(t);
        }
        mission.setTechniques(techniques);
        
        // Комментарий
        NodeList commentNodes = doc.getElementsByTagName("comment");
        if (commentNodes.getLength() > 0) {
            mission.setComment(commentNodes.item(0).getTextContent());
        }
        
        return mission;
    }
    
    private String getTagValue(String tag, Document doc) {
        NodeList nodes = doc.getElementsByTagName(tag);
        if (nodes.getLength() > 0) {
            return nodes.item(0).getTextContent();
        }
        return "";
    }
    
    private String getChildTagValue(String tag, Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            NodeList nodes = element.getElementsByTagName(tag);
            if (nodes.getLength() > 0) {
                return nodes.item(0).getTextContent();
            }
        }
        return "";
    }
    
    @Override
    public boolean canParse(String fileName) {
        return fileName.toLowerCase().endsWith(".xml");
    }
}