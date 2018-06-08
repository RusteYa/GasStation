package com.kpfu.itis.gasstation.entities.social;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {
    @Id
    @Column(name = "series", length = 64, nullable = false)
    private String series;

    @Column(name = "username", length = 64, nullable = false)
    private String userName;

    @Column(name = "token", length = 64, nullable = false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_used", nullable = false)
    private Date lastUsed;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}