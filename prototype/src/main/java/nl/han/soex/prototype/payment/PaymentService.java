package nl.han.soex.prototype.payment;

import nl.han.soex.prototype.payment.domain.PaymentMethodStrategy;
import nl.han.soex.prototype.payment.domain.PaymentRequest;
import nl.han.soex.prototype.payment.domain.handlers.PaymentMethodHandler;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String handlePayment(PaymentRequest paymentRequest) {
        // Handle payment
        PaymentMethodHandler paymentMethodHandler = PaymentMethodStrategy.getHandler(paymentRequest.getPaymentMethod());

        if(paymentMethodHandler == null){
            throw new IllegalArgumentException("Payment method not supported!");
        }

        return paymentMethodHandler.handlePayment(paymentRequest);
    }
}
