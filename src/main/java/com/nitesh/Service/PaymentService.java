package com.nitesh.Service;

import com.nitesh.model.Order;
import com.nitesh.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService  {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
