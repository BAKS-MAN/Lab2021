package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.google.api.client.http.HttpStatusCodes.STATUS_CODE_OK;
import static org.epam.util.DateTimeUtil.generateTimestampCustomPattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SlackService {

    public static void postNotification(String message) {
        ApiService apiService = new ApiService();
        String messageBody = String.format("{\"text\": \"%s, %s\"}", message,
                generateTimestampCustomPattern("dd-MM-yyyy, HH:mm:ss"));
        apiService.postSlackNotification(messageBody).then().statusCode(STATUS_CODE_OK);
    }
}
