package com.janita.excel.common.entity;

import lombok.Data;

/**
 * Created by Janita on 2017/6/12 0012- 上午 10:55
 * 该类是：
 */
public class BaseDto {

    private String sheetId;
    private Long clazzId;
    private String clazzName;
    private String testTime;
    private String testName;
    private int testStuNum;

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTestStuNum() {
        return testStuNum;
    }

    public void setTestStuNum(int testStuNum) {
        this.testStuNum = testStuNum;
    }

    public BaseDto() {
    }

    public BaseDto(String sheetId, Long clazzId, String clazzName, String testTime, String testName, int testStuNum) {
        this.sheetId = sheetId;
        this.clazzId = clazzId;
        this.clazzName = clazzName;
        this.testTime = testTime;
        this.testName = testName;
        this.testStuNum = testStuNum;
    }

    public static BaseDto getBaseDto() {
        return new BaseDto("123",12L,"clazzName","20190708","testName",20);
    }
}
