package webapp3.webapp3.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;


@Embeddable
public class PrimaryKeyUserTable implements Serializable {

    @Column(name="user_ID")
    private Long user_ID;

    @Column(name="exercise_table_ID")
    private Long exercise_table_ID;

    @Column(name="date")
    private Date date;

    public PrimaryKeyUserTable(){

    }

    public PrimaryKeyUserTable(Long user_ID, Long exercise_table_ID){
        this.user_ID = user_ID;
        this.exercise_table_ID = exercise_table_ID;
        this.date = new Date();
    }
}

