package nl.han.soex.payment;

public interface PaymentMethod {
    void handlePayment() throws InterruptedException;
    void canclePayment();
}
