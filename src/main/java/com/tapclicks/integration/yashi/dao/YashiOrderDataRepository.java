package com.tapclicks.integration.yashi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tapclicks.integration.yashi.domain.ZzYashiCgn;
import com.tapclicks.integration.yashi.domain.ZzYashiCgnData;
import com.tapclicks.integration.yashi.domain.ZzYashiOrder;
import com.tapclicks.integration.yashi.domain.ZzYashiOrderData;

public interface YashiOrderDataRepository extends CrudRepository<ZzYashiOrderData, Integer> {
	List<ZzYashiOrderData> findById(int id);

	List<ZzYashiOrderData> findByZzYashiOrderAndLogDate(ZzYashiOrder yashiOrder, Date logDate);

	ZzYashiOrderData save(ZzYashiOrderData yashiOrderData);
}
