import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Point {
    private int x;
    private double y;
    private double R;
    private String time;
    private SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");

    public Point() {
    }

    Point (int x, double y, double r, Date date){
        this.x = x;
        this.y = y;
        this.R = r;
        this.time = formatForDateNow.format(date);
    }

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return R;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Point{";
    }
}