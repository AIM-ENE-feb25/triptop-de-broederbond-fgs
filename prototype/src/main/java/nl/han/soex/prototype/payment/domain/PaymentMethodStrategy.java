package nl.han.soex.prototype.payment.domain;

import nl.han.soex.prototype.payment.domain.handlers.PaymentMethodHandler;
import nl.han.soex.prototype.payment.domain.handlers.PaypalHandler;
import nl.han.soex.prototype.payment.domain.handlers.StripeHandler;

import java.util.Map;

public class PaymentMethodStrategy {
    private static final Map<PaymentMethod, PaymentMethodHandler> handlers = Map.of(
            PaymentMethod.PAYPAL, new PaypalHandler(),
            PaymentMethod.STRIPE, new StripeHandler()
    );

    public static PaymentMethodHandler getHandler(PaymentMethod method) {
        return handlers.getOrDefault(method, null);
    }
}
