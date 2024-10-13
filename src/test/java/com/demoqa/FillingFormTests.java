package com.demoqa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static com.demoqa.expectedresult.ExpectedResultConstants.*;
import static com.demoqa.locators.FormPageLocators.*;
import static com.demoqa.testdata.FakerTestData.*;
import static com.demoqa.testdata.NegativeTestData.*;
import static com.demoqa.testdata.TestData.SUBJECT_FIELD_TEST_DATA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FillingFormTests {
    private static final String URL = "https://demoqa.com/automation-practice-form";
    private static final String PICTURE_PATH = "src/test/resources/testPicture.jpg";
    private static String fakerFirstName;
    private static String fakerLastName;
    private static String fakerEmail;
    private static String fakerPhoneNumber;
    private String fakerAddress;
    private List<String> expectedResultList;
    private File picture;
    private String pictureName;

    private void initTestData() {
        fakerFirstName = getFirstName();
        fakerLastName = getLastName();
        fakerEmail = getEmail();
        fakerPhoneNumber = getPhoneNumber();
        fakerAddress = getAddress();
    }
    private void initFile() {
        picture = new File(PICTURE_PATH);
        pictureName = picture.getName();
    }
    private void getExpectedResult() {
        expectedResultList = new ArrayList<>();
        String fullName = fakerFirstName + " " + fakerLastName;
        expectedResultList.add(fullName);
        expectedResultList.add(fakerEmail);
        expectedResultList.add(MALE_ER);
        expectedResultList.add(fakerPhoneNumber);
        expectedResultList.add(DATE_OF_BIRTH_ER);
        expectedResultList.add(SUBJECT_FIELD_TEST_DATA);
        expectedResultList.add(SPORTS_ER);
        expectedResultList.add(pictureName);
        expectedResultList.add(fakerAddress);
        expectedResultList.add(NCR_AND_DELHI_ER);
    }
    private void pickDateOfBirthStep() {
        $(DATE_OF_BIRTH_DATA_PICKER).click();
        $(MONTH_DATA_PICKER_SELECT).click();
        $(JANUARY_MONTH).click();
        $(YEAR_DATA_PICKER_SELECT).click();
        $(YEAR_2020).click();
        $(FIRST_JANUARY_2020_DAY).click();
    }
    public static Stream<Arguments> paramTestData() {
        return Stream.of(
                arguments(EMPTY_FIRST_NAME, fakerLastName, fakerEmail, MALE_RADIO_BUTTON, fakerPhoneNumber),
                arguments(fakerFirstName, EMPTY_LAST_NAME, fakerEmail, MALE_RADIO_BUTTON, fakerPhoneNumber),
                arguments(fakerFirstName, fakerLastName, INVALID_EMAIL, MALE_RADIO_BUTTON, fakerPhoneNumber),
                arguments(fakerFirstName, fakerLastName, fakerEmail,MOBILE_FIELD, fakerPhoneNumber),
                arguments(fakerFirstName, fakerLastName, fakerEmail, MALE_RADIO_BUTTON, INVALID_PHONE)
        );
    }
    @BeforeEach
    public void prepare() {
        initTestData();
        initFile();
        getExpectedResult();
        open(URL);
    }

    @Test
    public void fillingFormPositiveTest() {
        $(FIRST_NAME_FIELD).setValue(fakerFirstName);
        $(LAST_NAME_FIELD).setValue(fakerLastName);
        $(EMAIL_FIELD).setValue(fakerEmail);
        $(MALE_RADIO_BUTTON).click();
        $(MOBILE_FIELD).setValue(fakerPhoneNumber);
        pickDateOfBirthStep();
        $(SUBJECT_FIELD_INPUT).setValue(SUBJECT_FIELD_TEST_DATA);
        $(SUBJECT_FIELD_DROPDOWN).click();
        $(SPORTS_CHECKBOX).click();
        $(CHOOSING_FILE_BUTTON).uploadFile(picture);
        $(CURRENT_ADDRESS).setValue(fakerAddress);
        $(STATE_DROPDOWN).click();
        $(NCR_STATE).click();
        $(CITY_DROPDOWN).click();
        $(DELHI_CITY).click();
        $(SUBMIT_BUTTON).click();
        List<String> actualResultList = $$(SUCCESS_WINDOW_ELEMENTS).texts();
        assertThat(actualResultList).containsExactlyInAnyOrderElementsOf(expectedResultList);
    }

    @ParameterizedTest
    @MethodSource("paramTestData")
    public void fillingFormNegativeTests(String firstName, String lastName, String email, By genderRadio, String phone) {
        $(FIRST_NAME_FIELD).setValue(firstName);
        $(LAST_NAME_FIELD).setValue(lastName);
        $(EMAIL_FIELD).setValue(email);
        $(genderRadio).click();
        $(MOBILE_FIELD).setValue(phone);
        $(SUBMIT_BUTTON).scrollIntoView(true);
        $(SUBMIT_BUTTON).click();
        assertThat($(VALIDATION_PAGE).getText()).isEqualTo(VALIDATE_PAGE_ER);
    }
}
