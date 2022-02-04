package com.benchraid.benchraid.entities;

import com.benchraid.benchraid.enums.Role;
import com.benchraid.benchraid.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="t_t")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task extends Entitie{

    @Column (length = 86, name = "title")
    private String title;

    @Column (name = "description")
    private String desc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public String getDateInSimpleFormat(Date date){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return df.format(date);
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}