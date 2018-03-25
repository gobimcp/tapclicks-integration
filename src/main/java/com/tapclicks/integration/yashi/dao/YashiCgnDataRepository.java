package com.tapclicks.integration.yashi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tapclicks.integration.yashi.domain.ZzYashiCgn;
import com.tapclicks.integration.yashi.domain.ZzYashiCgnData;

public interface YashiCgnDataRepository extends CrudRepository<ZzYashiCgnData, Integer> {
	List<ZzYashiCgnData> findById(int id);

	List<ZzYashiCgnData> findByZzYashiCgnAndLogDate(ZzYashiCgn yashiCgn, Date logDate);

	ZzYashiCgnData save(ZzYashiCgnData yashiCampaignData);
}
