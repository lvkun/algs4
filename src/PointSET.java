
public class PointSET {
    private SET<Point2D> set;
    
    /**
     * construct an empty set of points
     */
    public PointSET() {
        set = new SET<Point2D>();
    }
    
    /**
     * @return is the set empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * @return number of points in the set
     */
    public int size() {
        return set.size();
    }
    
    
    /**
     * add the point p to the set (if it is not already in the set)
     * @param p point
     */
    public void insert(Point2D p) {
        set.add(p);
    }
    
    /**
     * @param p point
     * @return does the set contain the point p?
     */
    public boolean contains(Point2D p) {
        return set.contains(p);
    }
    
    /**
     * draw all of the points to standard draw
     */
    public void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }
    
    /**
     * @param rect rectangle
     * @return all points in the set that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> stack = new Stack<Point2D>();
        
        for (Point2D p : set) {
            if (rect.contains(p)) {
                stack.push(p);
            }
        }
        
        return stack;
    }
    
    /**
     * @param p point
     * @return a nearest neighbor in the set to p; null if set is empty
     */
    public Point2D nearest(Point2D p) {
        if (size() == 0) {
            return null;
        }
        
        Point2D neighbor = null;
        
        for (Point2D point : set) {
            if (neighbor == null) {
                neighbor = point;
            }
            
            if (point.distanceSquaredTo(p) < neighbor.distanceSquaredTo(p)) {
                neighbor = point;
            }
        }
        
        return neighbor;
    }
}
