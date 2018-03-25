package com.tapclicks.integration.yashi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tapclicks.integration.yashi.domain.ZzYashiCgn;

public interface YashiCgnRepository extends CrudRepository<ZzYashiCgn, Integer> {
	List<ZzYashiCgn> findByCampaignId(int campaignId);

	List<ZzYashiCgn> findByYashiCampaignId(int yashiCampaignId);

	ZzYashiCgn save(ZzYashiCgn yashiCampaign);
}
