package entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Rustem.
 */
@Entity
public class Message {
    private String header;
    private String body;
    private int id;
    private Integer idSender;
    private Integer idReceiver;
    private User userByIdSender;
    private User userByIdReceiver;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                Objects.equals(header, message.header) &&
                Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(header, body, id);
    }

    @Basic
    @Column(name = "id_sender", nullable = true)
    public Integer getIdSender() {
        return idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
    }

    @Basic
    @Column(name = "id_receiver", nullable = true)
    public Integer getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(Integer idReceiver) {
        this.idReceiver = idReceiver;
    }

    @ManyToOne
    @JoinColumn(name = "id_sender", referencedColumnName = "id")
    public User getUserByIdSender() {
        return userByIdSender;
    }

    public void setUserByIdSender(User userByIdSender) {
        this.userByIdSender = userByIdSender;
    }

    @ManyToOne
    @JoinColumn(name = "id_receiver", referencedColumnName = "id")
    public User getUserByIdReceiver() {
        return userByIdReceiver;
    }

    public void setUserByIdReceiver(User userByIdReceiver) {
        this.userByIdReceiver = userByIdReceiver;
    }
}
