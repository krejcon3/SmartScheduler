/**
 * Created by krejcir on 29.4.14.
 */
public abstract class Item {
    public int day;
    private int start;
    private int length;
    private String subjectName;

    public Item() {

    }

    public Item(int day, int start, int length) {
        this.day = day;
        this.start = start;
        this.length = length;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) throws SmartScheduleException {
        switch (day) {
            case Day.MONDAY:
            case Day.TUESDAY:
            case Day.WEDNESDAY:
            case Day.THURSDAY:
            case Day.FRIDAY:
            case Day.SATURDAY:
            case Day.SUNDAY:
                this.day = day;
                break;
            default:
                throw new SmartScheduleException("Undefined day of week.");
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
