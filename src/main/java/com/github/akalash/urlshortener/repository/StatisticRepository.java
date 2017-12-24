package com.github.akalash.urlshortener.repository;

import com.github.akalash.urlshortener.domain.Account;
import com.github.akalash.urlshortener.domain.Statistic;
import com.github.akalash.urlshortener.domain.UrlBinding;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository of statistic manage.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 23.12.2017
 */
public interface StatisticRepository extends CrudRepository<Statistic, Long> {

    /**
     * Insert new record if bunch of (original_url, account_id) does not exist and increment attempts otherwise
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value =
            "merge INTO STATISTIC(original_url, attempts, account_id) " +
                    "KEY(original_url, account_id) " +
                    "VALUES(" +
                    ":originalUrl, " +
                    "coalesce((SELECT attempts + 1 FROM statistic WHERE original_url=:originalUrl AND account_id=:accountId),1)," +
                    ":accountId" +
                    ")",
            nativeQuery = true)
    void upsert(@Param("originalUrl") String originalUrl, @Param("accountId") Long accountId);

    List<Statistic> findByAccount(Account account);
}
