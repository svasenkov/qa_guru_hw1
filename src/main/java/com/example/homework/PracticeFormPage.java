package com.example.homework;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class PracticeFormPage {
    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement userEmail = $("#userEmail");
    private SelenideElement maleRadio = $x("//input[@value='Male']/following-sibling::label");
    private SelenideElement otherRadio = $("input[value='Other']");
    private SelenideElement mobileNumberInput = $("#userNumber");
    private SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    @Getter(AccessLevel.NONE)
    private SelenideElement yearDropdown = $(".react-datepicker__year-select");
    @Getter(AccessLevel.NONE)
    private SelenideElement monthDropdown = $(".react-datepicker__month-select");
    private SelenideElement subjectsInput = $("#subjectsInput");
    private ElementsCollection selectedSubjects = $$(".subjects-auto-complete__multi-value__label");
    private SelenideElement sportsCheckbox = $x("//div[contains(normalize-space(.), 'Sports') and contains(@class, 'checkbox')]");
    private SelenideElement readingCheckbox = $x("//div[contains(normalize-space(.), 'Reading') and contains(@class, 'checkbox')]");
    private SelenideElement musicCheckbox = $x("//div[contains(normalize-space(.), 'Music') and contains(@class, 'checkbox')]");
    private SelenideElement uploadPictureInput = $("#uploadPicture");
    private SelenideElement currentAddressTextarea = $("#currentAddress");
    private SelenideElement stateDropdown = $("#state input");
    private SelenideElement cityDropdown = $("#city input");

    public void selectBirthDay(LocalDate date) {
        String year = String.valueOf(date.getYear());
        String month = StringUtils.capitalize(date.getMonth().name().toLowerCase());
        int day = date.getDayOfMonth();

        dateOfBirthInput.click();
        yearDropdown.selectOption(year);
        monthDropdown.selectOption(month);
        $x(String.format("//div[contains(@aria-label, '%s') and text() = %s]", month, day)).click();
    }

    public void pickSubject(String subject) {
        subjectsInput.val(subject);
        subjectsInput.pressEnter();
    }

    public void selectState(String state) {
        stateDropdown.val(state);
        stateDropdown.pressEnter();
    }

    public void selectCity(String state) {
        cityDropdown.val(state);
        cityDropdown.pressEnter();
    }
}
