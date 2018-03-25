package com.tapclicks.integration.yashi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tapclicks.integration.yashi.domain.ZzYashiCgn;
import com.tapclicks.integration.yashi.domain.ZzYashiOrder;

public interface YashiOrderRepository extends CrudRepository<ZzYashiOrder, Integer> {
	List<ZzYashiOrder> findByOrderId(int orderId);

	List<ZzYashiOrder> findByZzYashiCgn(ZzYashiCgn yashiCgn);

	List<ZzYashiOrder> findByYashiOrderId(int yashiOrderId);

	ZzYashiOrder save(ZzYashiOrder yashiOrder);
}
