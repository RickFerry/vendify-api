package com.vendify.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vendify.model.Sale;
import com.vendify.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsService {
    private final SaleRepository saleRepository;

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    public void sendSms(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
        String msg = "Olá, " + sale.getSellerName() + ", você foi destaque de vendas na data " + date + ", com um total de R$ "
                + String.format("%.2f", sale.getAmount()) + " vendidos!";

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, msg).create();

        log.info("SMS enviado com sucesso com id: {}", message.getSid());
    }
}
