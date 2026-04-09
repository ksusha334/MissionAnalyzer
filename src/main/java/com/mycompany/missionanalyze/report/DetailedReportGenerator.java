/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.report;

import com.mycompany.missionanalyze.model.Curse;
import com.mycompany.missionanalyze.model.Mission;
import com.mycompany.missionanalyze.model.Sorcerer;
import com.mycompany.missionanalyze.model.Technique;
import java.util.List;
import java.util.Map;

/**
 *
 * @author march
 */
public class DetailedReportGenerator implements ReportGenerator {
    @Override
    public String generate(Mission mission) {
        StringBuilder sb = new StringBuilder();
        sb.append("   ПОДРОБНЫЙ ОТЧЁТ О МИССИИ\n");
        
        sb.append(" ОСНОВНАЯ ИНФОРМАЦИЯ\n");
        sb.append("  ID миссии:    ").append(mission.getMissionId()).append("\n");
        sb.append("  Дата:         ").append(mission.getDate()).append("\n");
        sb.append("  Место:        ").append(mission.getLocation()).append("\n");
        sb.append("  Результат:    ").append(mission.getOutcome()).append("\n");
        sb.append("  Ущерб:        ").append(mission.getDamageCost()).append("\n\n");
        
        Curse curse = mission.getCurse();
        if (curse != null) {
            sb.append("ПРОКЛЯТИЕ\n");
            sb.append("  Название: ").append(curse.getName()).append("\n");
            sb.append("  Уровень: ").append(curse.getThreatLevel()).append("\n\n");
        }
        
        List<Sorcerer> sorcerers = mission.getSorcerers();
        if (sorcerers != null && !sorcerers.isEmpty()) {
            sb.append("МАГИ (").append(sorcerers.size()).append(")\n");
            for (Sorcerer s : sorcerers) {
                sb.append(" - ").append(s.getName());
                if (s.getRank() != null) {
                    sb.append(" [").append(s.getRank()).append("]");
                }
                if (s.getAge() > 0) {
                    sb.append(", ").append(s.getAge()).append(" лет");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        
        List<Technique> techniques = mission.getTechniques();
        if (techniques != null && !techniques.isEmpty()) {
            sb.append("ИСПОЛЬЗОВАННЫЕ ТЕХНИКИ\n");
            for (Technique t : techniques) {
                sb.append("  - ").append(t.getName());
                sb.append(" (").append(t.getType()).append(")\n");
                sb.append("    Владелец: ").append(t.getOwner());
                sb.append(" | Урон: ").append(t.getDamage()).append("\n");
            }
            sb.append("\n");
        }
        
        String comment = mission.getComment();
        if (comment != null && !comment.trim().isEmpty()) {
            sb.append("КОММЕНТАРИЙ\n");
            sb.append("  ").append(comment).append("\n\n");
        }
        
        Map<String, Object> extensions = mission.getExtensions();
        if (extensions != null && !extensions.isEmpty()) {
            sb.append("ДОПОЛНИТЕЛЬНЫЕ БЛОКИ ДАННЫХ\n");
            
            for (Map.Entry<String, Object> entry : extensions.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                sb.append("  ").append(key).append(":\n");
                if (value instanceof Map) {
                    Map<?, ?> map = (Map<?, ?>) value;
                    for (Map.Entry<?, ?> subEntry : map.entrySet()) {
                        sb.append("    ").append(subEntry.getKey()).append(": ").append(subEntry.getValue()).append("\n");
                    }
                } else {
                    sb.append("    ").append(value).append("\n");
                }
            }
            sb.append("\n");
        } else {
            sb.append(" ДОПОЛНИТЕЛЬНЫЕ БЛОКИ ДАННЫХ\n");
            sb.append("  Нет дополнительных данных\n\n");
        }
        
        return sb.toString();
    }
}
