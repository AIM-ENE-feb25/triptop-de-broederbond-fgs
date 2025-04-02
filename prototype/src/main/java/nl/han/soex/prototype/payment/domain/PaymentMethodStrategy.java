package nl.han.soex.prototype.payment.domain;

import nl.han.soex.prototype.payment.domain.handlers.PaymentMethodHandler;
import nl.han.soex.prototype.payment.domain.handlers.PaypalHandler;
import nl.han.soex.prototype.payment.domain.handlers.StripeHandler;

import java.util.Map;

public class PaymentMethodStrategy {
    private static final Map<PaymentMethods, PaymentMethodHandler> handlers = Map.of(
            PaymentMethods.PAYPAL, new PaypalHandler(),
            PaymentMethods.STRIPE, new StripeHandler()
    );

    public static PaymentMethodHandler getHandler(PaymentMethods method) {
        return handlers.getOrDefault(method, null);
    }
}
