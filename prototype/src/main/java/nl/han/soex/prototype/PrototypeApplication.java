package nl.han.soex.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
