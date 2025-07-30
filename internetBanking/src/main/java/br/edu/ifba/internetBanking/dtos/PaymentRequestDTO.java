package br.edu.ifba.internetBanking.dtos;

import java.math.BigDecimal;

public record PaymentRequestDTO(Long accountId, BigDecimal value, String description) {}
