package com.paypal.controller;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.entity.PaymentRequest;
import com.paypal.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {
    private final PayPalService payPalService;

    @Autowired
    public PaymentController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/{create-payment}")
    @ResponseStatus(HttpStatus.CREATED)
    public Payment createPayment(@RequestBody PaymentRequest paymentRequest) throws PayPalRESTException {
        double amount = paymentRequest.getAmount();
        String currency = paymentRequest.getCurrency();
        String method = paymentRequest.getMethod();
        String intent = paymentRequest.getIntent();
        String description = paymentRequest.getDescription();
        String cancelUrl = paymentRequest.getCancelUrl();
        String successUrl = paymentRequest.getSuccessUrl();

        return payPalService.createPayment(amount, currency, method, intent, description, cancelUrl, successUrl);
    }
}
