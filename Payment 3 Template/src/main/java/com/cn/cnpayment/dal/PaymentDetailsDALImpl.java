package com.cn.cnpayment.dal;

import com.cn.cnpayment.entity.PaymentDetails;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDetailsDALImpl implements PaymentDetailsDAL {

    // Autowire the EntityManager object
    @Autowired
    private EntityManagerFactory entityManager;

    @Override
    public PaymentDetails getById(int id) {
        return entityManager.find(PaymentDetails.class, id);
    }

    @Override
    public List<PaymentDetails> getAllPaymentDetails() {
        String jpql = "SELECT pd FROM PaymentDetails pd";
        TypedQuery<PaymentDetails> query = entityManager.createQuery(jpql, PaymentDetails.class);
        return query.getResultList();
    }

    @Override
    public void save(PaymentDetails paymentDetails) {
        entityManager.persist(paymentDetails);
    }

    @Override
    public void delete(int id) {
        PaymentDetails paymentDetails = getById(id);
        if (paymentDetails != null) {
            entityManager.remove(paymentDetails);
        }
    }

    @Override
    public void update(PaymentDetails paymentDetails) {
        entityManager.merge(paymentDetails);
    }

    @Override
    public List<PaymentDetails> getByCurrency(String currency) {
        String jpql = "SELECT pd FROM PaymentDetails pd WHERE pd.currency = :currency";
        TypedQuery<PaymentDetails> query = entityManager.createQuery(jpql, PaymentDetails.class);
        query.setParameter("currency", currency);
        return query.getResultList();
    }
}
