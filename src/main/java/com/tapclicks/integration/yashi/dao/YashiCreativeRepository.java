package com.tapclicks.integration.yashi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tapclicks.integration.yashi.domain.ZzYashiCgn;
import com.tapclicks.integration.yashi.domain.ZzYashiCreative;
import com.tapclicks.integration.yashi.domain.ZzYashiOrder;

public interface YashiCreativeRepository extends CrudRepository<ZzYashiCreative, Integer> {
	List<ZzYashiCreative> findByCreativeId(int creativeId);

	List<ZzYashiCreative> findByYashiCreativeId(int yashiCreativeId);

	List<ZzYashiCreative> findByZzYashiOrder(ZzYashiOrder yashiOrder);

	ZzYashiCreative save(ZzYashiCreative yashiCreative);
}
