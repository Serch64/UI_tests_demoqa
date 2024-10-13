package com.demoqa.locators;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;

public class FormPageLocators {
    public static final By FIRST_NAME_FIELD = byId("firstName");
    public static final By LAST_NAME_FIELD = byId("lastName");
    public static final By EMAIL_FIELD = byId("userEmail");
    public static final By MALE_RADIO_BUTTON = byXpath(".//input[@id='gender-radio-1']/parent::div");
    public static final By MOBILE_FIELD = byId("userNumber");
    public static final By DATE_OF_BIRTH_DATA_PICKER = byId("dateOfBirthInput");
    public static final By MONTH_DATA_PICKER_SELECT = byClassName("react-datepicker__month-select");
    public static final By JANUARY_MONTH = byXpath(".//option[text()='January']");
    public static final By YEAR_DATA_PICKER_SELECT = byClassName("react-datepicker__year-select");
    public static final By YEAR_2020 = byXpath(".//option[text()='2020']");
    public static final By FIRST_JANUARY_2020_DAY = byXpath(".//div[@aria-label='Choose Wednesday, January 1st, 2020']");
    public static final By SUBJECT_FIELD_INPUT = byId("subjectsInput");
    public static final By SUBJECT_FIELD_DROPDOWN = byId("react-select-2-option-0");
    public static final By SPORTS_CHECKBOX = byXpath(".//input[@id='hobbies-checkbox-1']/parent::div");
    public static final By CHOOSING_FILE_BUTTON = byId("uploadPicture");
    public static final By CURRENT_ADDRESS = byId("currentAddress");
    public static final By STATE_DROPDOWN = byId("state");
    public static final By NCR_STATE = byId("react-select-3-option-0");
    public static final By CITY_DROPDOWN = byId("city");
    public static final By DELHI_CITY = byId("react-select-4-option-0");
    public static final By SUBMIT_BUTTON = byId("submit");
    public static final By SUCCESS_WINDOW_ELEMENTS = byXpath(".//tbody/tr/td[2]");
    public static final By VALIDATION_PAGE= byXpath(".//h5");
}


