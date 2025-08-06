package br.edu.ifba.internetBanking.dtos;

import java.math.BigDecimal;

public record WithdrawalRequestDTO(Long accountId, BigDecimal value) {}
