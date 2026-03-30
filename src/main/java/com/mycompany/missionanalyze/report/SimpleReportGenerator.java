/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.report;

import com.mycompany.missionanalyze.model.Mission;
import com.mycompany.missionanalyze.model.Curse;
import com.mycompany.missionanalyze.model.Sorcerer;
import com.mycompany.missionanalyze.model.Technique;
/**
 *
 * @author march
 */
public class SimpleReportGenerator implements ReportGenerator{
    
    public String generate(Mission mission) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n   МИССИЯ \n");
        sb.append("ID: ").append(mission.getMissionId()).append("\n");
        sb.append("Дата: ").append(mission.getDate()).append("\n");
        sb.append("Место: ").append(mission.getLocation()).append("\n");
        sb.append("Результат: ").append(mission.getOutcome()).append("\n");
        sb.append("Ущерб: ").append(mission.getDamageCost()).append("\n");
        
        Curse curse = mission.getCurse();
        if (curse != null) {
            sb.append("\n   ПРОКЛЯТИЕ \n");
            sb.append("Название: ").append(curse.getName()).append("\n");
            sb.append("Уровень: ").append(curse.getThreatLevel()).append("\n");
        }
        
        if (mission.getSorcerers() != null && !mission.getSorcerers().isEmpty()) {
            sb.append("\n   МАГИ \n");
            for (Sorcerer s : mission.getSorcerers()) {
                sb.append("• ").append(s.getName()).append(" (").append(s.getRank()).append(")\n");
            }
        }
        
        if (mission.getTechniques() != null && !mission.getTechniques().isEmpty()) {
            sb.append("\n   ТЕХНИКИ \n");
            for (Technique t : mission.getTechniques()) {
                sb.append("• ").append(t.getName()).append(" - ").append(t.getOwner())
                  .append(" (урон: ").append(t.getDamage()).append(")\n");
            }
        }
        
        sb.append("\nКомментарий: ");
        String comment = mission.getComment();
        if (comment != null && !comment.trim().isEmpty()) {
            sb.append(comment).append("\n");
        } else {
            sb.append("отсутствует\n");
        }
        
        return sb.toString();
    }
}