package com.kpfu.itis.gasstation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "appusers")
public class AppUser implements UserDetails, SocialUserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "login", unique = true, nullable = false, length = 40)
    private String login;

    @Column(name = "hashedpassword", nullable = false, length = 128)
    private String hashedPassword;

    @Column(name = "name", length = 40)
    private String name;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Column(name = "deposit")
    private int deposit;

    @Transient
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private AppRole appRole;

    @JsonIgnore
    @OneToMany(mappedBy = "messageSender", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Message> sentMessages;

    @JsonIgnore
    @OneToMany(mappedBy = "messageReceiver", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Message> receivedMessages;

    @JsonIgnore
    @OneToMany(mappedBy = "ticketClient", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Ticket> clientTickets;

    @JsonIgnore
    @OneToMany(mappedBy = "ticketStaff", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Ticket> staffTickets;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", deposit=" + deposit +
                ", appRole=" + appRole +
                ", sentMessages=" + sentMessages +
                ", receivedMessages=" + receivedMessages +
                ", clientTickets=" + clientTickets +
                ", staffTickets=" + staffTickets +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Ticket> getClientTickets() {
        return clientTickets;
    }

    public void setClientTickets(List<Ticket> clientTickets) {
        this.clientTickets = clientTickets;
    }

    public List<Ticket> getStaffTickets() {
        return staffTickets;
    }

    public void setStaffTickets(List<Ticket> staffTickets) {
        this.staffTickets = staffTickets;
    }

    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    @Override
    @JsonIgnore
    public String getUserId() {
        return getId() + "";
    }
}
