package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.PaymentDetailsDAL;
import com.cn.cnpayment.entity.PaymentDetails;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.NotFoundException;
import com.cn.cnpayment.exception.InvalidInputException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentDetailsService {

    @Autowired
    private PaymentDetailsDAL paymentDetailsDAL;

    public PaymentDetails getPaymentDetailsById(int id) {
        PaymentDetails paymentDetails = paymentDetailsDAL.getById(id);
        if (paymentDetails == null) {
            throw new NotFoundException("Payment not found with id: " + id);
        }
        return paymentDetails;
    }

    public List<PaymentDetails> getAllPaymentDetails() {
        List<PaymentDetails> paymentDetailsList = paymentDetailsDAL.getAllPaymentDetails();
        if (paymentDetailsList.isEmpty()) {
            throw new NotFoundException("No payment details found.");
        }
        return paymentDetailsList;
    }

    public void savePaymentDetails(PaymentDetails newPaymentDetails) {
        if (paymentDetailsDAL.getById(newPaymentDetails.getId()) != null) {
            throw new ElementAlreadyExistException("Payment with id " + newPaymentDetails.getId() + " already exists.");
        }
        paymentDetailsDAL.save(newPaymentDetails);
    }

    public void delete(int id) {
        paymentDetailsDAL.delete(id);
    }

    public void update(PaymentDetails paymentDetails) {
        if (paymentDetailsDAL.getById(paymentDetails.getId()) == null) {
            throw new NotFoundException("Payment not found with id: " + paymentDetails.getId());
        }
        paymentDetailsDAL.update(paymentDetails);
    }

    @Transactional
    public List<PaymentDetails> getByCurrency(String currency) {
        validateCurrency(currency);
        return paymentDetailsDAL.getByCurrency(currency);
    }

    private void validateCurrency(String currency) {
        List<String> allowedCurrencies = List.of("inr", "rupee", "dollar", "yen", "pound", "usd");
        if (!allowedCurrencies.contains(currency.toLowerCase())) {
            throw new InvalidInputException("Invalid currency. Allowed values: INR, Rupee, Dollar, Yen, Pound, USD");
        }
    }
}
