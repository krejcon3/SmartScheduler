import java.util.ArrayList;

/**
 * Created by krejcir on 29.4.14.
 */
public class Subject {
    private ArrayList<Exercise> exercises;
    private Lecture lecture;
    private String name;

    public Subject(String name) {
        this.exercises = new ArrayList<Exercise>();
        this.name = name;
    }

    public Subject(String name, Lecture lecture) {
        this.lecture = lecture;
        this.exercises = new ArrayList<Exercise>();
        this.name = name;
        this.lecture.setSubjectName(this.name);
    }

    public Subject(String name, Lecture lecture, ArrayList<Exercise> exercises) {
        this.lecture = lecture;
        this.exercises = exercises;
        this.name = name;
        this.lecture.setSubjectName(this.name);
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public void addExercise(Exercise exercise) {
        exercise.setSubjectName(this.name);
        this.exercises.add(exercise);
    }
}
