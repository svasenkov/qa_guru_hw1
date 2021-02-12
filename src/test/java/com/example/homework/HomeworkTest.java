package com.example.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.open;
import static java.time.format.DateTimeFormatter.ofPattern;

public class HomeworkTest {
    private final PracticeFormPage formPage = new PracticeFormPage();
    private final Faker faker = Faker.instance();

    @BeforeAll
    public static void setUpAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.startMaximized = true;
    }

    @BeforeEach
    public void setUp() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void practiceForm() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String emailAddress = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().subscriberNumber(10);
        LocalDate birthDay = LocalDate.of(2000, Month.APRIL, 2);
        String subject = "Hindi";
        String imageName = "tiger.jpg";
        String address = faker.address().fullAddress();
        String state = "Haryana";
        String city = "Panipat";

        formPage.getFirstNameInput().shouldBe(Condition.visible).val(firstName);
        formPage.getLastNameInput().val(lastName);
        formPage.getUserEmail().val(emailAddress);
        formPage.getMaleRadio().click();
        formPage.getMobileNumberInput().val(phoneNumber);
        formPage.selectBirthDay(birthDay);
        formPage.pickSubject(subject);
        formPage.getSportsCheckbox().click();

        formPage.getUploadPictureInput().uploadFromClasspath(imageName);
        formPage.getCurrentAddressTextarea().val(address);
        formPage.selectState(state);
        formPage.selectCity(city);

        formPage.getFirstNameInput().shouldHave(value(firstName));
        formPage.getLastNameInput().shouldHave(value(lastName));
        formPage.getUserEmail().shouldHave(value(emailAddress));
        formPage.getMaleRadio().find(byXpath("./preceding-sibling::input")).shouldHave(Condition.checked);
        formPage.getMobileNumberInput().shouldHave(value(phoneNumber));
        formPage.getDateOfBirthInput().shouldHave(value(birthDay.format(ofPattern("dd MMM yyyy"))));
        formPage.getSelectedSubjects().shouldHave(exactTexts(subject));
        formPage.getSportsCheckbox().find(byXpath("./input")).shouldHave(Condition.checked);
        formPage.getUploadPictureInput().shouldHave(value(imageName));
        formPage.getCurrentAddressTextarea().shouldHave(value(address));
        formPage.getStateDropdown().find(byXpath("./ancestor::div[@id='state']")).shouldHave(text(state));
        formPage.getCityDropdown().find(byXpath("./ancestor::div[@id='city']")).shouldHave(text(city));
    }
}
