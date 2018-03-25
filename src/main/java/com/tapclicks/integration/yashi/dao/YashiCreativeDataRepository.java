package com.tapclicks.integration.yashi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tapclicks.integration.yashi.domain.ZzYashiCgn;
import com.tapclicks.integration.yashi.domain.ZzYashiCgnData;
import com.tapclicks.integration.yashi.domain.ZzYashiCreative;
import com.tapclicks.integration.yashi.domain.ZzYashiCreativeData;

public interface YashiCreativeDataRepository extends CrudRepository<ZzYashiCreativeData, Integer> {
	List<ZzYashiCreativeData> findById(int id);

	List<ZzYashiCreativeData> findByZzYashiCreativeAndLogDate(ZzYashiCreative yashiCreative, Date logDate);

	ZzYashiCreativeData save(ZzYashiCreativeData yashiCreativeData);
}
