package org.epam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.epam.util.AuthApiService.getAccessToken;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebDriverDriverSessionService {
    public static void setReportPortalSessionToken(WebDriver driver) {
        getJavascriptExecutor(driver).executeScript("localStorage.setItem(arguments[0], arguments[1])",
                "token", Token.getAuthorizeJson());
    }

    public static void clearReportPortalSessionToken(WebDriver driver) {
        getJavascriptExecutor(driver).executeScript("localStorage.clear()");
    }

    private static JavascriptExecutor getJavascriptExecutor(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    @AllArgsConstructor
    @Data
    static class Token {
        String type;
        String value;

        public static String getAuthorizeJson() {
            Token token = new Token("bearer", getAccessToken());
            try {
                return new ObjectMapper().writeValueAsString(token);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Failed to create authorize json: \n" + e.getMessage());
            }
        }
    }
}
