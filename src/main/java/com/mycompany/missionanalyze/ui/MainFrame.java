/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.ui;

import com.mycompany.missionanalyze.model.Mission;
import com.mycompany.missionanalyze.parser.JsonParser;
import com.mycompany.missionanalyze.parser.ParserRegistry;
import com.mycompany.missionanalyze.parser.MissionParser;
import com.mycompany.missionanalyze.parser.ParserRegistry;
import com.mycompany.missionanalyze.parser.TextParser;
import com.mycompany.missionanalyze.parser.XmlParser;
import com.mycompany.missionanalyze.parser.YamlParser;
import com.mycompany.missionanalyze.parser.NoNameParser;
import com.mycompany.missionanalyze.report.DetailedReportGenerator;
import com.mycompany.missionanalyze.report.ReportGenerator;
import com.mycompany.missionanalyze.report.SimpleReportGenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *
 * @author march
 */
public class MainFrame extends JFrame{
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private ParserRegistry parserRegistry;
    private JComboBox<String> reportSelector;
    
    public MainFrame() {
        parserRegistry = new ParserRegistry();
        
        parserRegistry.register(new JsonParser());
        parserRegistry.register(new XmlParser());
        parserRegistry.register(new TextParser());
        parserRegistry.register(new YamlParser());
        parserRegistry.register(new NoNameParser());
        
        setTitle("Анализатор миссий магов");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel topPanel = new JPanel();
        
        JButton openButton = new JButton("Открыть файл миссии");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        
        String[] reportTypes = {
            "1. Базовый отчёт",
            "2. Расширенный отчёт"
        };
        reportSelector = new JComboBox<String>(reportTypes);
        
        topPanel.add(openButton);
        topPanel.add(new JLabel("    Тип отчёта: "));
        topPanel.add(reportSelector);
        
        add(topPanel, BorderLayout.NORTH);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(openButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        fileChooser = new JFileChooser();
    }
    
    private void openFile() {
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                MissionParser parser = parserRegistry.getParser(file.getName());
                Mission mission = parser.parse(file);
                ReportGenerator reportGenerator = createReportGenerator();
                String report = reportGenerator.generate(mission);
                textArea.setText(report);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Ошибка при загрузке файла: " + e.getMessage(), 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private ReportGenerator createReportGenerator() {
        int selectedIndex = reportSelector.getSelectedIndex();
         switch (selectedIndex) {
            case 0:
                return new SimpleReportGenerator();
            case 1:
                return new DetailedReportGenerator();
            default:
                return new SimpleReportGenerator();
        }
    }
}