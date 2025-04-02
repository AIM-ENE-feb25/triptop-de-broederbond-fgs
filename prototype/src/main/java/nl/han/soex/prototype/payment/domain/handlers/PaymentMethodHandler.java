package nl.han.soex.prototype.payment.domain.handlers;

import com.stripe.exception.StripeException;
import nl.han.soex.prototype.payment.domain.PaymentRequest;

import java.math.BigDecimal;

public interface PaymentMethodHandler {
    String handlePayment(PaymentRequest paymentRequest);
    void canclePayment();
}
