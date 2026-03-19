/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.ui;

import com.mycompany.missionanalyze.model.Mission;
import com.mycompany.missionanalyze.parser.ParserFactory;
import com.mycompany.missionanalyze.parser.MissionParser;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 *
 * @author march
 */
public class MainFrame extends JFrame{
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private ParserFactory parserFactory;
    
    public MainFrame() {
        parserFactory = new ParserFactory();
        
        setTitle("Анализатор миссий магов");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Текстовая область для вывода
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Кнопка
        JButton openButton = new JButton("Открыть файл миссии");
        openButton.setFont(new Font("Arial", Font.PLAIN, 14));
        openButton.addActionListener(e -> openFile());
        
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
                MissionParser parser = parserFactory.getParser(file.getName());
                Mission mission = parser.parse(file);
                textArea.setText(mission.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Ошибка при загрузке файла: " + e.getMessage(), 
                    "Ошибка", 
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    } 
}