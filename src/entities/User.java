package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Rustem.
 */
@Entity
public class User {
    private String password;
    private String name;
    private String login;
    private int id;
    private Integer deposit;
    private Integer idUserGroup;
    private Collection<Message> messagesById;
    private Collection<Message> messagesById_0;
    private Collection<Ticket> ticketsById;
    private Collection<Ticket> ticketsById_0;
    private UserGroup userGroupByIdUserGroup;

    @Basic
    @Column(name = "password", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "deposit", nullable = true)
    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(deposit, user.deposit);
    }

    @Override
    public int hashCode() {

        return Objects.hash(password, name, login, id, deposit);
    }

    @Basic
    @Column(name = "id_user_group", nullable = true)
    public Integer getIdUserGroup() {
        return idUserGroup;
    }

    public void setIdUserGroup(Integer idUserGroup) {
        this.idUserGroup = idUserGroup;
    }

    @OneToMany(mappedBy = "userByIdSender")
    public Collection<Message> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<Message> messagesById) {
        this.messagesById = messagesById;
    }

    @OneToMany(mappedBy = "userByIdReceiver")
    public Collection<Message> getMessagesById_0() {
        return messagesById_0;
    }

    public void setMessagesById_0(Collection<Message> messagesById_0) {
        this.messagesById_0 = messagesById_0;
    }

    @OneToMany(mappedBy = "userByIdClient")
    public Collection<Ticket> getTicketsById() {
        return ticketsById;
    }

    public void setTicketsById(Collection<Ticket> ticketsById) {
        this.ticketsById = ticketsById;
    }

    @OneToMany(mappedBy = "userByIdStaff")
    public Collection<Ticket> getTicketsById_0() {
        return ticketsById_0;
    }

    public void setTicketsById_0(Collection<Ticket> ticketsById_0) {
        this.ticketsById_0 = ticketsById_0;
    }

    @ManyToOne
    @JoinColumn(name = "id_user_group", referencedColumnName = "id")
    public UserGroup getUserGroupByIdUserGroup() {
        return userGroupByIdUserGroup;
    }

    public void setUserGroupByIdUserGroup(UserGroup userGroupByIdUserGroup) {
        this.userGroupByIdUserGroup = userGroupByIdUserGroup;
    }
}
