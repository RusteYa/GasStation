package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "approles")
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "appRole", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<AppUser> usersWithAppRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
