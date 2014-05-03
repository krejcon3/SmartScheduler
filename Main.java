import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        // Generate ASF schedule
        Subject ASF = new Subject("ASF", new Lecture(Day.TUESDAY, 11, 2));
        ASF.addExercise(new Exercise(Day.TUESDAY, 7, 2));
        ASF.addExercise(new Exercise(Day.TUESDAY, 9, 2));
        ASF.addExercise(new Exercise(Day.TUESDAY, 5, 2));
        ASF.addExercise(new Exercise(Day.WEDNESDAY, 9, 2));
        ASF.addExercise(new Exercise(Day.THURSDAY, 7, 2));
        ASF.addExercise(new Exercise(Day.THURSDAY, 3, 2));
        ASF.addExercise(new Exercise(Day.THURSDAY, 5, 2));
        ASF.addExercise(new Exercise(Day.TUESDAY, 13, 2));
        subjects.add(ASF);

        // Generate KAJ schedule
        Subject KAJ = new Subject("KAJ", new Lecture(Day.MONDAY, 3, 2));
        KAJ.addExercise(new Exercise(Day.MONDAY, 5, 2));
        KAJ.addExercise(new Exercise(Day.MONDAY, 7, 2));
        KAJ.addExercise(new Exercise(Day.MONDAY, 11, 2));
        subjects.add(KAJ);

        // Generate TAL schedule
        Subject TAL = new Subject("TAL", new Lecture(Day.TUESDAY, 2, 3));
        TAL.addExercise(new Exercise(Day.THURSDAY, 1, 2));
        TAL.addExercise(new Exercise(Day.THURSDAY, 3, 2));
        TAL.addExercise(new Exercise(Day.THURSDAY, 5, 2));
        subjects.add(TAL);

        // Generate KO schedule
        Subject KO = new Subject("KO", new Lecture(Day.TUESDAY, 5, 3));
        KO.addExercise(new Exercise(Day.THURSDAY, 1, 2));
        KO.addExercise(new Exercise(Day.THURSDAY, 3, 2));
        KO.addExercise(new Exercise(Day.THURSDAY, 5, 2));
        KO.addExercise(new Exercise(Day.THURSDAY, 7, 2));
        KO.addExercise(new Exercise(Day.WEDNESDAY, 9, 2));
        KO.addExercise(new Exercise(Day.WEDNESDAY, 11, 2));
        KO.addExercise(new Exercise(Day.WEDNESDAY, 13, 2));
        KO.addExercise(new Exercise(Day.THURSDAY, 11, 2));
        KO.addExercise(new Exercise(Day.THURSDAY, 9, 2));
        subjects.add(KO);

        // Generate OSP schedule
        Subject OSP = new Subject("OSP", new Lecture(Day.WEDNESDAY, 9, 2));
        OSP.addExercise(new Exercise(Day.THURSDAY, 11, 2));
        OSP.addExercise(new Exercise(Day.THURSDAY, 3, 2));
        OSP.addExercise(new Exercise(Day.THURSDAY, 5, 2));
        OSP.addExercise(new Exercise(Day.THURSDAY, 7, 2));
        OSP.addExercise(new Exercise(Day.THURSDAY, 9, 2));
        OSP.addExercise(new Exercise(Day.THURSDAY, 13, 2));
        subjects.add(OSP);

        // Generate PDA schedule
        Subject PDA = new Subject("PDA", new Lecture(Day.MONDAY, 11, 2));
        PDA.addExercise(new Exercise(Day.MONDAY, 5, 2));
        PDA.addExercise(new Exercise(Day.MONDAY, 7, 2));
        PDA.addExercise(new Exercise(Day.MONDAY, 9, 2));
        subjects.add(PDA);

        Solver solver = new Solver(subjects);
        Printer printer = new Printer();

        try {
            solver.setStrictLevel(Solver.STRICT_LECTURE_COVERS_LIMITATION);
            solver.forbidDay(Day.TUESDAY);
            solver.setEarliestItem(5);
            solver.setLatestItem(12);
            Schedule schedule = solver.solve();
            printer.print(schedule, "./schedule.html");
        } catch (SmartScheduleException e) {
            printer.print(e, "./schedule.html");
            System.out.println(e.getMessage());
        }
    }
}
