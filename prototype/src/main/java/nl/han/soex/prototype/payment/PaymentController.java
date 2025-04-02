package nl.han.soex.prototype.payment;

import nl.han.soex.prototype.payment.domain.PaymentMethodStrategy;
import nl.han.soex.prototype.payment.domain.handlers.PaymentMethodHandler;
import nl.han.soex.prototype.payment.domain.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<String> handlePayment(@RequestBody PaymentRequest paymentRequest) throws InterruptedException {
        String paymentLink = paymentService.handlePayment(paymentRequest);

        return ResponseEntity.ok(paymentLink);
    }
}
