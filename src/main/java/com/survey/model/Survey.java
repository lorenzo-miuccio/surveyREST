package com.survey.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "survey_table")
public class Survey {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_category", nullable = false, insertable = false, updatable = false)
    private Category category;

    @Column(name = "id_mail")
    private String id_mail;

    @Column(name = "description")
    private String description;

    @Column(name = "publish_date")
    private Date publish_date;

    @Column(name = "ending_date")
    private Date ending_date;

    public Survey(long id, String name, Category category, String id_mail, String description, Date publish_date, Date ending_date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.id_mail = id_mail;
        this.description = description;
        this.publish_date = publish_date;
        this.ending_date = ending_date;
    }

    public Survey() {

    }


}
