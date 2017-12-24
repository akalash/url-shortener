package com.github.akalash.urlshortener.domain;

import javax.persistence.*;

/**
 * Represent of bind of urls in db.
 *
 * @author Kalashnikov Anton <akalash.dev@gmail.com>
 * @since 24.12.2017
 */
@Entity
@Table(name = "url_bindings")
public class UrlBinding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "short_url", unique = true)
    private String shortUrl;

    @Column(name = "redirect_type")
    @Enumerated(EnumType.STRING)
    private RedirectType redirectType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    public UrlBinding() {
    }

    public UrlBinding(String originalUrl, String shortUrl, RedirectType redirectType, Account account) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.redirectType = redirectType;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public RedirectType getRedirectType() {
        return redirectType;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlBinding that = (UrlBinding) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (originalUrl != null ? !originalUrl.equals(that.originalUrl) : that.originalUrl != null) return false;
        if (shortUrl != null ? !shortUrl.equals(that.shortUrl) : that.shortUrl != null) return false;
        if (redirectType != that.redirectType) return false;
        return account != null ? account.equals(that.account) : that.account == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (originalUrl != null ? originalUrl.hashCode() : 0);
        result = 31 * result + (shortUrl != null ? shortUrl.hashCode() : 0);
        result = 31 * result + (redirectType != null ? redirectType.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UrlBinding{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", redirectType=" + redirectType +
                ", account=" + account +
                '}';
    }
}
