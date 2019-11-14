/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Score implements Comparable<Score>{
    private int score;

    public Score(int val){
        super();
        this.score = val;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int val) {
        this.score = val;
    }

    public static void main(String[] args) {

    }

    @Override
    public int compareTo(Score o) {
        int thatScore = o.getScore();
        if(this.score < thatScore) return -1;
        if(this.score > thatScore) return 1;
        return 0;
    }
}
