/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Comparator;

public class Student {
    private int score;
    private String name;

    private static class ByScore implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.score - w.score;
        }
    }
    public static final Comparator<Student> BY_SCORE = new ByScore();

    private static class ByName implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.name.compareTo(w.name);
        }
    }
    public static final Comparator<Student> BY_NAME = new ByName();


    public Student(int val, String name){
        super();
        this.score = val;
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int val) {
        this.score = val;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

    }
}
