import java.util.Arrays;


/**
 * @author brlv
 *
 */
public class Fast {
    
    /**
     * collinear points number
     */
    private static int COLNUM = 4;
    
    /**
     * @param pointInfo.
     * @return point object.
     */
    private static Point createPoint(String pointInfo) {
        String[] point = pointInfo.trim().split("\\s+");
        if (point.length != 2) {
            throw new java.lang.IllegalArgumentException();
        }
        int x = Integer.parseInt(point[0]);
        int y = Integer.parseInt(point[1]);
        
        return new Point(x, y);
    }
    
    /**
     * @param args file path.
     * @return point array.
     */
    private static Point[] load(String[] args) {
        In in = new In(args[0]);
        int pointNumber = Integer.parseInt(in.readLine());
        
        Point[] points = new Point[pointNumber];
        int index = 0;
        
        if (pointNumber > 0) {
            while (!in.isEmpty() && index < pointNumber) {
                String line = in.readLine();
                if (line.trim().length() == 0) {
                    continue;
                }
                points[index++] = createPoint(line);
            }
        }
        
        in.close();
        return points;
    }
    
    /**
     * print out collinear result
     * @param points array sorted by slope order (first element)
     * @param start start point index
     * @param end end point index
     */
    private static void printOut(Point[] points, int start, int end) {
        Arrays.sort(points, start, end);        
        for (int i = start; i < end; i++) { 
            StdOut.print(points[i] + " -> ");
        }
        StdOut.println(points[0]);
        
        points[start].drawTo(points[0]);
    }
   
    /**
     * @param args file path which contains points info.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new java.lang.IllegalArgumentException();
        }
        
        Point[] points = load(args);
        Point[] pointsBySlope = new Point[points.length];
        
        for (int i = 0; i < points.length; i++) {
            pointsBySlope[i] = points[i];
        }
        
        Arrays.sort(points);
//        printOut(points, 1, points.length);
        for (int i = points.length -1; i >= COLNUM - 1; i--) {
            Point point = points[i];
            
            Arrays.sort(pointsBySlope, 0, pointsBySlope.length, 
                    point.SLOPE_ORDER);
            
            int start = 1;
            double ignoreSlope = point.slopeTo(point);
            for (int j = 1; j < pointsBySlope.length; j++) {
                
                Point curPoint = pointsBySlope[j];
                double curSlope = point.slopeTo(curPoint);
                double lastSlope = point.slopeTo(pointsBySlope[j-1]);
                
                if (lastSlope != curSlope) {
                    if (j - start >= COLNUM - 1) {
                        printOut(pointsBySlope, start, j);
                    }
                    
                    start = j;
                }
                
                if (point.compareTo(curPoint) < 0) {
                    ignoreSlope = point.slopeTo(curPoint);
                }
                
                if (ignoreSlope == curSlope) {
                    start = j + 1;
                    continue;
                }
            }
            
            if (pointsBySlope.length - start >= COLNUM - 1) {
                printOut(pointsBySlope, start, pointsBySlope.length);
            }
        }
    }
}
