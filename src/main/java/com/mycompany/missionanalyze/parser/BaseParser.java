/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missionanalyze.parser;

import com.mycompany.missionanalyze.model.Mission;
import com.mycompany.missionanalyze.validation.RequiredFieldsValidator;
import com.mycompany.missionanalyze.validation.Validator;
import java.io.File;
import java.util.List;

/**
 *
 * @author march
 */
public abstract class BaseParser implements MissionParser {   
    private Validator validator;
    
    public BaseParser() {
        this.validator = new RequiredFieldsValidator();
    }
    
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
    
    @Override
    public final Mission parse(File file) throws Exception {
        validateFile(file);       
        logStart(file);       
        Mission mission = doParse(file); 
        validateMission(mission);
        postProcess(mission);      
        logSuccess(file);        
        return mission;
    }
    
    protected void validateMission(Mission mission) throws Exception {
        if (validator != null) {
            List<String> errors = validator.validate(mission);
            if (!errors.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder("Ошибки валидации:\n");
                for (String error : errors) {
                    errorMessage.append("  - ").append(error).append("\n");
                }
                throw new Exception(errorMessage.toString());
            }
        }
    }
    
    protected void validateFile(File file) throws Exception {
        if (file == null) {
            throw new IllegalArgumentException("Файл не может быть null");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не существует: " + file.getAbsolutePath());
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("Указанный путь не является файлом: " + file.getAbsolutePath());
        }
        if (file.length() == 0) {
            throw new IllegalArgumentException("Файл пуст: " + file.getName());
        }
    }
    
    protected abstract Mission doParse(File file) throws Exception;
    protected void postProcess(Mission mission) {}
    protected void logStart(File file) {
        System.out.println("Начало парсинга: " + file.getName());
    }
    
    protected void logSuccess(File file) {
        System.out.println("Успешно распарсен: " + file.getName());
    }
}
