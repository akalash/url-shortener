package com.github.akalash.urlshortener.services;

import com.github.akalash.urlshortener.domain.Account;
import com.github.akalash.urlshortener.domain.Statistic;
import com.github.akalash.urlshortener.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for return statistic
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@Service
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    /**
     * Return statistic
     *
     * @param account authorized account
     * @return (original url, number of use)
     */
    public Map<String, Long> getStatistic(Account account) {
        return statisticRepository.findByAccount(account).stream()
                .collect(Collectors.toMap(Statistic::getOriginalUrl, Statistic::getCount));
    }
}
