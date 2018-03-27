package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Rustem.
 */
@Entity
public class Ticket {
    private String header;
    private String body;
    private int id;
    private Timestamp date;
    private Status statusByIdStatus;
    private Integer idClient;
    private Integer idStaff;
    private Integer idStatus;
    private User userByIdClient;
    private User userByIdStaff;

    @Basic
    @Column(name = "header", nullable = true, length = 40)
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Basic
    @Column(name = "body", nullable = true, length = -1)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Objects.equals(header, ticket.header) &&
                Objects.equals(body, ticket.body) &&
                Objects.equals(date, ticket.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(header, body, id, date);
    }

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    public Status getStatusByIdStatus() {
        return statusByIdStatus;
    }

    public void setStatusByIdStatus(Status statusByIdStatus) {
        this.statusByIdStatus = statusByIdStatus;
    }

    @Basic
    @Column(name = "id_client", nullable = true)
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "id_staff", nullable = true)
    public Integer getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(Integer idStaff) {
        this.idStaff = idStaff;
    }

    @Basic
    @Column(name = "id_status", nullable = true)
    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    public User getUserByIdClient() {
        return userByIdClient;
    }

    public void setUserByIdClient(User userByIdClient) {
        this.userByIdClient = userByIdClient;
    }

    @ManyToOne
    @JoinColumn(name = "id_staff", referencedColumnName = "id")
    public User getUserByIdStaff() {
        return userByIdStaff;
    }

    public void setUserByIdStaff(User userByIdStaff) {
        this.userByIdStaff = userByIdStaff;
    }
}
