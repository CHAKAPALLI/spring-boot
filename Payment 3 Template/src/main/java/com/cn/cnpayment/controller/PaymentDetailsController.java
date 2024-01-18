import com.cn.cnpayment.entity.PaymentDetails;
import com.cn.cnpayment.exception.NotFoundException;
import com.cn.cnpayment.service.PaymentDetailsService;
import com.cn.cnpayment.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
public class PaymentDetailsController {

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @GetMapping("/id/{id}")
    public PaymentDetails getPaymentDetailsById(@PathVariable int id) {
        PaymentDetails paymentDetails = paymentDetailsService.getPaymentDetailsById(id);
        if (paymentDetails == null) {
            throw new NotFoundException("Payment not found with id: " + id);
        }
        return paymentDetails;
    }

    @PostMapping("/save")
    public void savePaymentDetails(@RequestBody PaymentDetails paymentDetails) {
        paymentDetailsService.savePaymentDetails(paymentDetails);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable int id) {
        paymentDetailsService.delete(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody PaymentDetails paymentDetails) {
        if (paymentDetailsService.getPaymentDetailsById(paymentDetails.getId()) == null) {
            throw new NotFoundException("Payment not found with id: " + paymentDetails.getId());
        }
        paymentDetailsService.update(paymentDetails);
    }

    @GetMapping("/allPaymentDetails")
    public List<PaymentDetails> getAllPayments() {
        List<PaymentDetails> paymentDetailsList = paymentDetailsService.getAllPaymentDetails();
        if (paymentDetailsList.isEmpty()) {
            throw new NotFoundException("No payment details found.");
        }
        return paymentDetailsList;
    }

    @GetMapping("/currency/{currency}")
    public List<PaymentDetails> getByCurrency(@PathVariable String currency) {
        validateCurrency(currency);
        List<PaymentDetails> paymentDetailsList = paymentDetailsService.getByCurrency(currency);
        if (paymentDetailsList.isEmpty()) {
            throw new NotFoundException("No payment details found for currency: " + currency);
        }
        return paymentDetailsList;
    }

    private void validateCurrency(String currency) {
        List<String> allowedCurrencies = List.of("inr", "dollar", "usd", "pound", "yen", "rupee");
        if (!allowedCurrencies.contains(currency.toLowerCase())) {
            throw new InvalidInputException("Invalid currency. Allowed values: INR, Dollar, USD, Pound, Yen, Rupee");
        }
    }
}
