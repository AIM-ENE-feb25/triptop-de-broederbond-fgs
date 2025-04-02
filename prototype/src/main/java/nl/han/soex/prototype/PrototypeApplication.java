package nl.han.soex.prototype;

import nl.han.soex.prototype.payment.domain.PaymentMethods;
import nl.han.soex.prototype.payment.domain.PaymentRequest;
import nl.han.soex.prototype.payment.domain.handlers.PaymentMethodHandler;
import nl.han.soex.prototype.payment.domain.handlers.StripeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class PrototypeApplication {

    public static void main(String[] args) {
//        PaymentMethodHandler stripe = new StripeHandler();
//        stripe.handlePayment(new PaymentRequest(
//                List.of("1", "1"),
//                PaymentMethods.STRIPE,
//                BigDecimal.valueOf(100))
//        );

        SpringApplication.run(PrototypeApplication.class, args);
    }

}
