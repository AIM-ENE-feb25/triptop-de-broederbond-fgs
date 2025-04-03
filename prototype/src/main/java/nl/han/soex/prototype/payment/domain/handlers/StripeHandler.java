package nl.han.soex.prototype.payment.domain.handlers;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import nl.han.soex.prototype.payment.domain.PaymentRequest;

import java.math.BigDecimal;

public class StripeHandler implements PaymentMethodHandler{

    private final String API_KEY =
            "sk_test_51R7ZC2Peokx2Ees81jN2RRw6GJUt0rcefCljs4se6XimMwj3sjliS1ldDse7MNhkmzUzAQnSOmvRN0iiJEVBnCVu00eOCt7VyK";

    @Override
    public String handlePayment(PaymentRequest paymentRequest) {
        Stripe.apiKey = API_KEY;

        try {
            Product product = createProduct(paymentRequest.getAmount());

            PaymentLink paymentLink = createPaymentLink(product.getDefaultPrice());

            return paymentLink.getUrl();
        } catch (StripeException e) {
            System.out.println("Payment failed!");
        }

        return null;
    }

    /*
    Stripe works with products, so we need to create a product to be able to create a payment.
    */
    private Product createProduct(BigDecimal amount) throws StripeException {
        ProductCreateParams params = ProductCreateParams
                .builder()
                .setName("PaymentRequest")
                .setDefaultPriceData(ProductCreateParams.DefaultPriceData
                        .builder()
                        .setCurrency("eur")
                        // price is cents
                        .setUnitAmountDecimal(amount.multiply(BigDecimal.valueOf(100)))
                        .build())
                .setActive(true)
                .build();
        return Product.create(params);
    }

    private PaymentLink createPaymentLink(String amount) throws StripeException {
        PaymentLinkCreateParams params = PaymentLinkCreateParams
                .builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setPrice(amount)
                                .setQuantity(1L)
                                .build()
                )
                .build();

        return PaymentLink.create(params);
    }
}
