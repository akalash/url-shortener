package com.github.akalash.urlshortener.domain;

import javax.persistence.*;

/**
 * Represent of statistic info in db.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@Entity
@Table(name = "statistic")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "attempts")
    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Statistic() {
    }

    public Statistic(String originalUrl, Long count, Account account) {
        this.originalUrl = originalUrl;
        this.count = count;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public Long getCount() {
        return count;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistic statistic = (Statistic) o;

        if (id != null ? !id.equals(statistic.id) : statistic.id != null) return false;
        if (originalUrl != null ? !originalUrl.equals(statistic.originalUrl) : statistic.originalUrl != null) return false;
        if (count != null ? !count.equals(statistic.count) : statistic.count != null) return false;
        return account != null ? account.equals(statistic.account) : statistic.account == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (originalUrl != null ? originalUrl.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", count=" + count +
                ", account=" + account +
                '}';
    }
}
