package br.edu.ifba.internetBanking.dtos;

import java.math.BigDecimal;

public record DepositRequestDTO(Long accountId, BigDecimal value, String description) {}
