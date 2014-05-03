import java.util.ArrayList;
import java.util.List;

/**
 * Created by krejcir on 29.4.14.
 */
public class Solver {

    public static final int STRICT_NONE = 0;
    public static final int STRICT_EXERCISES = 1;
    public static final int STRICT_ALL = 2;

    private ArrayList<Subject> subjects;
    private int strictLevel;
    private Schedule schedule;

    public Solver(ArrayList<Subject> subjects) {
        this.subjects = subjects;
        this.strictLevel = STRICT_ALL;
        this.schedule = new Schedule();
    }

    public Schedule solve() throws SmartScheduleException {
        int schedulingTable[][] = new int[5][14];

        schedulingTable = this.generateLectureSchedule(schedulingTable);

        if (this.strictLevel != STRICT_ALL) {
            schedulingTable = new int[5][14];
        }

        schedulingTable = this.generateExerciseSchedule(schedulingTable);

        return this.schedule;
    }

    private int[][] generateLectureSchedule(int[][] schedulingTable) throws SmartScheduleException {
        for (Subject subject : this.subjects) {
            Lecture lecture = subject.getLecture();
            for (int i = 0; i < lecture.getLength(); i++) {

                // STRICT ALL
                if (schedulingTable[lecture.getDay() - 1][lecture.getStart() - 1 + i] > 0 && this.strictLevel == STRICT_ALL) {
                    throw new SmartScheduleException("Schedule not exist, covering lectures found.");
                }
                schedulingTable[lecture.getDay() - 1][lecture.getStart() - 1 + i]++;
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
                if (schedulingTable[exercise.getDay() - 1][exercise.getStart() - 1 + i] != 0) {
                    collision = true;
                }
            }
            if (collision) {
                continue;
            }
            for (int i = 0; i < exercise.getLength(); i++) {
                schedulingTable[exercise.getDay() - 1][exercise.getStart() - 1 + i]++;
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
                    schedulingTable[exercise.getDay() - 1][exercise.getStart() - 1 + i]--;
                }
                continue;
            }
        }
        throw new SmartScheduleException("No possible schedule in this branch.");
    }

    public void setStrictLevel(int strictLevel) throws SmartScheduleException {
        switch (strictLevel) {
            case STRICT_NONE:
            case STRICT_EXERCISES:
            case STRICT_ALL:
                this.strictLevel = strictLevel;
                break;
            default:
                throw new SmartScheduleException("Invalid argument: " + strictLevel);
        }
    }
}
