package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeEach
    void setup() {
        //Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[placeholder='Город']").setValue(validUser.getCity());
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(firstMeetingDate);
        $("[name='name']").setValue(validUser.getName());
        $("[name='phone']").setValue(validUser.getPhone());
        $(".checkbox").click();
        $(".button__text").click();
        $("[data-test-id='success-notification']").shouldHave(text("Успешно! Встреча успешно запланирована на " + firstMeetingDate));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(secondMeetingDate);
        $(".button__text").click();
        $("[data-test-id='replan-notification']").shouldBe(visible).shouldHave(text("Необходимо подтверждение У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$(".button__text").findBy(text("Перепланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(text("Успешно! Встреча успешно запланирована на " + secondMeetingDate));
    }

}



