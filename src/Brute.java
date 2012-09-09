

/**
 * @author brlv
 *
 */
public class Brute {
    
    /**
     * @param pointInfo
     * @return point object
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
     * @param args file path which contains points info.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new java.lang.IllegalArgumentException();
        }
        
        Point[] points = load(args);
        int pointNumber = points.length;

        Quick.sort(points);
        
        for (int i = 0; i < pointNumber - 3; i++) {
            Point p1 = points[i];
            for (int j = i + 1; j < pointNumber - 2; j++) {
                Point p2 = points[j];
                for (int k = j + 1; k < pointNumber - 1; k++) {
                    Point p3 = points[k];
                    for (int l = k + 1; l < pointNumber; l++) {
                        Point p4 = points[l];
                        if ((p1.slopeTo(p2) == p1.slopeTo(p3))
                                && (p1.slopeTo(p2) == p1.slopeTo(p4))) {
                            StdOut.println(p1 + " -> " + p2 
                                    + " -> " + p3 + " -> " + p4);
                            
                            p1.drawTo(p4);
                        }
                        
                    }
                }
            }
        }
    }
}

