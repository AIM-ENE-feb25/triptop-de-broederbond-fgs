package nl.han.soex.prototype.payment.domain.handlers;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.ProductCreateParams;
import nl.han.soex.prototype.payment.domain.PaymentRequest;

public class StripeHandler implements PaymentMethodHandler{

    private final String API_KEY =
            "sk_test_51R7ZC2Peokx2Ees81jN2RRw6GJUt0rcefCljs4se6XimMwj3sjliS1ldDse7MNhkmzUzAQnSOmvRN0iiJEVBnCVu00eOCt7VyK";
    @Override
    public String handlePayment(PaymentRequest paymentRequest) {
        Stripe.apiKey = API_KEY;

        try {
            Product product = createProduct(paymentRequest.getAmount().longValue());
            PaymentLink paymentLink = createPaymentLink(product.getDefaultPrice());

            System.out.println(paymentLink.getUrl());
            return paymentLink.getUrl();
        } catch (StripeException e) {
            System.out.println("Payment failed!");
        }

        return null;
    }

    /*
    Stripe works with products, so we need to create a product to be able to create a payment.
    */
    private Product createProduct(Long amount) throws StripeException {
        ProductCreateParams params = ProductCreateParams
                .builder()
                .setName("PaymentRequest")
                .setDefaultPriceData(ProductCreateParams.DefaultPriceData
                        .builder()
                        .setCurrency("eur")
                        .setUnitAmount(amount)
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

    @Override
    public void canclePayment() {

    }
}
