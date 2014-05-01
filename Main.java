import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        // Generate ASF schedule
        Subject ASF = new Subject("ASF", new Lecture(Item.TUESDAY, 11, 2));
        ASF.addExercise(new Exercise(Item.TUESDAY, 7, 2));
        ASF.addExercise(new Exercise(Item.TUESDAY, 9, 2));
        ASF.addExercise(new Exercise(Item.TUESDAY, 5, 2));
        ASF.addExercise(new Exercise(Item.WEDNESDAY, 9, 2));
        ASF.addExercise(new Exercise(Item.THURSDAY, 7, 2));
        ASF.addExercise(new Exercise(Item.THURSDAY, 3, 2));
        ASF.addExercise(new Exercise(Item.THURSDAY, 5, 2));
        ASF.addExercise(new Exercise(Item.TUESDAY, 13, 2));
        subjects.add(ASF);

        // Generate KAJ schedule
        Subject KAJ = new Subject("KAJ", new Lecture(Item.MONDAY, 3, 2));
        KAJ.addExercise(new Exercise(Item.MONDAY, 5, 2));
        KAJ.addExercise(new Exercise(Item.MONDAY, 7, 2));
        KAJ.addExercise(new Exercise(Item.MONDAY, 11, 2));
        subjects.add(KAJ);

        // Generate TAL schedule
        Subject TAL = new Subject("TAL", new Lecture(Item.TUESDAY, 2, 3));
        TAL.addExercise(new Exercise(Item.THURSDAY, 1, 2));
        TAL.addExercise(new Exercise(Item.THURSDAY, 3, 2));
        TAL.addExercise(new Exercise(Item.THURSDAY, 5, 2));
        subjects.add(TAL);

        // Generate KO schedule
        Subject KO = new Subject("KO", new Lecture(Item.TUESDAY, 5, 3));
        KO.addExercise(new Exercise(Item.THURSDAY, 1, 2));
        KO.addExercise(new Exercise(Item.THURSDAY, 3, 2));
        KO.addExercise(new Exercise(Item.THURSDAY, 5, 2));
        KO.addExercise(new Exercise(Item.THURSDAY, 7, 2));
        KO.addExercise(new Exercise(Item.WEDNESDAY, 9, 2));
        KO.addExercise(new Exercise(Item.WEDNESDAY, 11, 2));
        KO.addExercise(new Exercise(Item.WEDNESDAY, 13, 2));
        KO.addExercise(new Exercise(Item.THURSDAY, 11, 2));
        KO.addExercise(new Exercise(Item.THURSDAY, 9, 2));
        subjects.add(KO);

        // Generate OSP schedule
        Subject OSP = new Subject("OSP", new Lecture(Item.WEDNESDAY, 9, 2));
        OSP.addExercise(new Exercise(Item.THURSDAY, 11, 2));
        OSP.addExercise(new Exercise(Item.THURSDAY, 3, 2));
        OSP.addExercise(new Exercise(Item.THURSDAY, 5, 2));
        OSP.addExercise(new Exercise(Item.THURSDAY, 7, 2));
        OSP.addExercise(new Exercise(Item.THURSDAY, 9, 2));
        OSP.addExercise(new Exercise(Item.THURSDAY, 13, 2));
        subjects.add(OSP);

        // Generate PDA schedule
        Subject PDA = new Subject("PDA", new Lecture(Item.MONDAY, 11, 2));
        PDA.addExercise(new Exercise(Item.MONDAY, 5, 2));
        PDA.addExercise(new Exercise(Item.MONDAY, 7, 2));
        PDA.addExercise(new Exercise(Item.MONDAY, 9, 2));
        subjects.add(PDA);

        Solver solver = new Solver(subjects);

        try {
            Schedule schedule = solver.solve();
            Printer printer = new Printer();
            printer.print(schedule, "./schedule.html");
        } catch (SmartScheduleException e) {
            System.out.println(e.getMessage());
        }
    }
}
