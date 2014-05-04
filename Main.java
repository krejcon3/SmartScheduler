import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        Subject A = new Subject("A", new Lecture(Day.MONDAY, 1, 1));
        A.addExercise(new Exercise(Day.FRIDAY, 2, 1));
        A.addExercise(new Exercise(Day.FRIDAY, 2, 1));
        A.addExercise(new Exercise(Day.FRIDAY, 2, 1));
        A.addExercise(new Exercise(Day.FRIDAY, 2, 1));
        A.addExercise(new Exercise(Day.MONDAY, 2, 1));
        subjects.add(A);

        Subject B = new Subject("B", new Lecture(Day.TUESDAY, 1, 1));
        B.addExercise(new Exercise(Day.TUESDAY, 2, 1));
        B.addExercise(new Exercise(Day.TUESDAY, 2, 1));
        B.addExercise(new Exercise(Day.TUESDAY, 2, 1));
        B.addExercise(new Exercise(Day.TUESDAY, 2, 1));
        B.addExercise(new Exercise(Day.TUESDAY, 2, 1));
        subjects.add(B);

        Subject E = new Subject("C", new Lecture(Day.WEDNESDAY, 1, 1));
        E.addExercise(new Exercise(Day.WEDNESDAY, 2, 1));
        E.addExercise(new Exercise(Day.WEDNESDAY, 2, 1));
        E.addExercise(new Exercise(Day.WEDNESDAY, 2, 1));
        E.addExercise(new Exercise(Day.WEDNESDAY, 2, 1));
        E.addExercise(new Exercise(Day.WEDNESDAY, 2, 1));
        subjects.add(E);

        Subject D = new Subject("D", new Lecture(Day.THURSDAY, 1, 1));
        D.addExercise(new Exercise(Day.THURSDAY, 2, 1));
        D.addExercise(new Exercise(Day.THURSDAY, 2, 1));
        D.addExercise(new Exercise(Day.THURSDAY, 2, 1));
        D.addExercise(new Exercise(Day.THURSDAY, 2, 1));
        D.addExercise(new Exercise(Day.THURSDAY, 2, 1));
        subjects.add(D);

        Subject C = new Subject("E", new Lecture(Day.FRIDAY, 1, 1));
        C.addExercise(new Exercise(Day.THURSDAY, 2, 1));
        C.addExercise(new Exercise(Day.THURSDAY, 2, 1));
        C.addExercise(new Exercise(Day.THURSDAY, 2, 1));
        C.addExercise(new Exercise(Day.THURSDAY, 2, 1));
        C.addExercise(new Exercise(Day.FRIDAY, 2, 1));
        subjects.add(C);

        Solver solver = new Solver(subjects);
        Printer printer = new Printer();

        try {
            Schedule schedule = solver.solve();
            printer.print(schedule, "./schedule.html");
        } catch (SmartScheduleException e) {
            System.out.println(e.getMessage());
            printer.print(e, "./schedule.html");
        }
    }
}
