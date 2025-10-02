package com.widetech.funds_transfer.enumeration;

import lombok.Getter;

@Getter
public enum Currency {
  IDR("Indonesian Rupiah", "Rp", "IDR", 0),
  USD("United States Dollar", "$", "USD", 2);

  private final String name;
  private final String symbol;
  private final String code;
  private final int scale; // number of decimal places

  Currency(String name, String symbol, String code, int scale) {
    this.name = name;
    this.symbol = symbol;
    this.code = code;
    this.scale = scale;
  }
}
