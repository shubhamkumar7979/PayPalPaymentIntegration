package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patload.SMSRequest;
import service.TwilioService;

@RestController
@RequestMapping
public class SMSController {

    private final TwilioService twilioService;

    @Autowired
    public SMSController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    //https://localhost:8080/send-sms
    @PostMapping("/send-sms")
    public void sendSMS(@RequestBody SMSRequest smsRequest) {
        String toPhoneNumber = smsRequest.getToPhoneNumber();
        String message = smsRequest.getMessage();

        twilioService.sendSMS(toPhoneNumber, message);
    }
}
