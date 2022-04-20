package com.virtuslab.internship.receipt;

import com.virtuslab.internship.discount.TenPercentDiscount;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public record Receipt(
        List<ReceiptEntry> entries,
        List<String> discounts,
        BigDecimal totalPrice) {

    public Receipt(List<ReceiptEntry> entries) {
        this(entries,
                new LinkedList<String>(), //bug: changed from null to LinkedList
                entries.stream()
                        .map(ReceiptEntry::totalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }
    public Receipt(List<ReceiptEntry> entries,List<String> discounts) {
        this(entries,
                discounts,
                entries.stream()
                        .map(ReceiptEntry::totalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }
}
