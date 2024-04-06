package com.hixtrip.sample.domain.inventory.repository;

/**
 *
 */
public interface InventoryRepository {


    int getInventory(String skuId);

    boolean updateInventory(String skuId, Long sellableQuantity, Long withholdingQuantity, Long occupiedQuantity);
}
