package webapp3.webapp3.model;

import javax.persistence.*;

@Entity
@Table(name="User_ExerciseTable")
public class UserExerciseTable {

    @EmbeddedId
    private PrimaryKeyUserTable primaryKeyUserTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("exercise_table_ID")
    private ExerciseTable exerciseTable;


    public UserExerciseTable(){

    }

    public UserExerciseTable(User user, ExerciseTable exerciseTable){
        this.user = user;
        this.exerciseTable = exerciseTable;
        this.primaryKeyUserTable = new PrimaryKeyUserTable(user.getId(), exerciseTable.getId());
    }

    public PrimaryKeyUserTable getPrimaryKeyUserTable() {
        return primaryKeyUserTable;
    }

    public void setPrimaryKeyUserTable(PrimaryKeyUserTable primaryKeyUserTable) {
        this.primaryKeyUserTable = primaryKeyUserTable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExerciseTable getExerciseTable() {
        return exerciseTable;
    }

    public void setExerciseTable(ExerciseTable exerciseTable) {
        this.exerciseTable = exerciseTable;
    }
}
