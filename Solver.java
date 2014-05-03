import java.util.ArrayList;

/**
 * Created by krejcir on 29.4.14.
 */
public class Solver {

    public static final int STRICT_NONE = 0;
    public static final int STRICT_EXERCISE_COVERS_LECTURE = 1;
    public static final int STRICT_LECTURE_COVERS_LECTURE = 3;
    public static final int STRICT_ALL = 2;

    public static final int WEEK_LENGTH = 5;
    public static final int DAY_LENGTH = 15;

    private ArrayList<Subject> subjects;
    private int strictLevel;
    private Schedule schedule;
    private ArrayList<Integer> disallowedDays;
    private int earliestItem = 0;
    private int latestItem = Solver.DAY_LENGTH;

    public Solver(ArrayList<Subject> subjects) {
        this.subjects = subjects;
        this.strictLevel = STRICT_ALL;
        this.schedule = new Schedule();
        this.disallowedDays = new ArrayList<Integer>();
    }

    public void forbidDay(int day) throws SmartScheduleException {
        switch (day) {
            case Day.MONDAY:
            case Day.TUESDAY:
            case Day.WEDNESDAY:
            case Day.THURSDAY:
            case Day.FRIDAY:
            case Day.SATURDAY:
            case Day.SUNDAY:
                this.disallowedDays.add(day);
                break;
            default:
                throw new SmartScheduleException("Undefined day of week.");
        }
    }

    public void setEarliestItem(int earliestItem) {
        this.earliestItem = earliestItem;
    }

    public void setLatestItem(int latestItem) {
        this.latestItem = latestItem;
    }

    public Schedule solve() throws SmartScheduleException {
        int schedulingTable[][] = new int[Solver.WEEK_LENGTH][Solver.DAY_LENGTH + 1];

        if (this.disallowedDays.size() > 0) {
            this.applyDisallowedDays(schedulingTable);
        }
        if (this.earliestItem > 0 || this.latestItem < Solver.DAY_LENGTH) {
            this.applyDisallowedHours(schedulingTable);
        }

        this.generateLectureSchedule(schedulingTable);

        if (this.strictLevel == STRICT_EXERCISE_COVERS_LECTURE) {
            schedulingTable = new int[Solver.WEEK_LENGTH][Solver.DAY_LENGTH + 1];
            if (this.disallowedDays.size() > 0) {
                this.applyDisallowedDays(schedulingTable);
            }
            if (this.earliestItem > 0 || this.latestItem < Solver.DAY_LENGTH) {
                this.applyDisallowedHours(schedulingTable);
            }
        }

        this.generateExerciseSchedule(schedulingTable);

        return this.schedule;
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[][] applyDisallowedDays(int[][] schedulingTable) {
        for (int day : this.disallowedDays) {
            for (int i = 0; i < schedulingTable[day].length; i++) {
                schedulingTable[day][i] = 1;
            }
        }
        return schedulingTable;
    }

    private int[][] applyDisallowedHours(int[][] schedulingTable) {
        for (int i = 0; i < schedulingTable.length; i++) {
            for (int j = 0; j < schedulingTable[i].length; j++) {
                if (j < this.earliestItem || j > this.latestItem) {
                    schedulingTable[i][j] = 1;
                }
            }
        }
        return schedulingTable;
    }

    private int[][] generateLectureSchedule(int[][] schedulingTable) throws SmartScheduleException {
        for (Subject subject : this.subjects) {
            Lecture lecture = subject.getLecture();
            for (int i = 0; i < lecture.getLength(); i++) {
                if (schedulingTable[lecture.getDay()][lecture.getStart() + i] > 0 && this.strictLevel == STRICT_ALL) {
                    throw new SmartScheduleException("Schedule not exist, covering lectures found.");
                }
                schedulingTable[lecture.getDay()][lecture.getStart() + i]++;
            }
            this.schedule.addItem(lecture);
        }
        return schedulingTable;
    }

    private int[][] generateExerciseSchedule(int[][] schedulingTable) throws SmartScheduleException {
        try {
            schedulingTable = this.checkExercises(schedulingTable, 0);
        } catch (SmartScheduleException e) {
            throw new SmartScheduleException("Schedule not exist, covering exercises found.");
        }
        return schedulingTable;
    }

    private int[][] checkExercises(int schedulingTable[][], int index) throws SmartScheduleException {
        Subject subject = this.subjects.get(index);
        for (Exercise exercise : subject.getExercises()) {
            boolean collision = false;
            for (int i = 0; i < exercise.getLength(); i++) {
                if (schedulingTable[exercise.getDay()][exercise.getStart() + i] != 0) {
                    collision = true;
                }
            }
            if (collision) {
                continue;
            }
            for (int i = 0; i < exercise.getLength(); i++) {
                schedulingTable[exercise.getDay()][exercise.getStart() + i]++;
            }
            this.schedule.addItem(exercise);
            try {
                if (index + 1 < this.subjects.size()) {
                    return this.checkExercises(schedulingTable.clone(), index + 1);
                } else {
                    return schedulingTable;
                }
            } catch (SmartScheduleException e) {
                this.schedule.popItem();
                for (int i = 0; i < exercise.getLength(); i++) {
                    schedulingTable[exercise.getDay()][exercise.getStart() + i]--;
                }
                continue;
            }
        }
        throw new SmartScheduleException("No possible schedule in this branch.");
    }

    public void setStrictLevel(int strictLevel) throws SmartScheduleException {
        switch (strictLevel) {
            case STRICT_NONE:
            case STRICT_EXERCISE_COVERS_LECTURE:
            case STRICT_ALL:
            case STRICT_LECTURE_COVERS_LECTURE:
                this.strictLevel = strictLevel;
                break;
            default:
                throw new SmartScheduleException("Invalid argument: " + strictLevel);
        }
    }
}
