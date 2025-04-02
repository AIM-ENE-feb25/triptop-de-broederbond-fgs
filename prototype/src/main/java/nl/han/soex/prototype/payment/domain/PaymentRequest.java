package nl.han.soex.prototype.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PaymentRequest {
    private List<Object> buildingBlocks;
    private PaymentMethods paymentMethod;
    private BigDecimal amount;
}
