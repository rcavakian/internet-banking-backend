package br.edu.ifba.internetBanking.dtos;

import java.math.BigDecimal;

public record WithdrawRequestDTO(Long accountId, BigDecimal value) {}
