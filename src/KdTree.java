
public class KdTree {
    
    private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL = false;
    
    private class Node {
        private Node left;
        private Node right;
        private RectHV rect;
        private Point2D value;
        
        private boolean type;
        private int size;

        public Node(Point2D val, boolean type, RectHV rect) {
            this.value = val;
            this.type = type;
            this.size = 1;
            this.rect = rect;
        }
        
        public int compare(double p1, double p2) {
            if (p1 > p2) {
                return 1;
            } else if (p1 < p2) {
                return -1;
            } else {
                return 0;
            }
        }
        
        public int compareTo(Point2D point) {
            if (this.type == HORIZONTAL) {
                int cmp = compare(this.value.x(), point.x());
                if (cmp != 0) {
                    return cmp;
                }
                return compare(this.value.y(), point.y());
            }
            
            int cmp = compare(this.value.y(), point.y());
            if (cmp != 0) {
                return cmp;
            }
            return compare(this.value.x(), point.x());
        }

        public int compareTo(RectHV r) {
            
            double x = this.value.x();
            double y = this.value.y();
            
            if (this.type == HORIZONTAL) {
                if (x > r.xmax()) {
                    // left
                    return 1;
                }
                if (x < r.xmin()) {
                    // right
                    return -1;
                }
            } else {
                if (y > r.ymax()) {
                    // bottom
                    return 1;
                }
                if (y < r.ymin()) {
                    // top
                    return -1;
                }
            }
            
            // interset
            return 0;
        }
        
        public RectHV getRect(Point2D p) {
            double xmin = this.rect.xmin();
            double xmax = this.rect.xmax();
            double ymin = this.rect.ymin();
            double ymax = this.rect.ymax();
            if (this.compareTo(p) > 0) {
                if (this.type == HORIZONTAL) {
                    xmax = this.value.x();
                } else {
                    ymax = this.value.y();
                }
            } else {
                if (this.type == HORIZONTAL) {
                    xmin = this.value.x();
                } else {
                    ymin = this.value.y();
                }
            }

            return new RectHV(xmin, ymin, xmax, ymax);
        }

    }
    
    private Node root = null;
    
    private Point2D minPoint = null;
    private double minDist = 0.0;
    /**
     * construct an empty set of points
     */
    public KdTree() {
        
    }
    
    /**
     * @return is the set empty?
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * @return number of points in the set
     */
    public int size() {
        return getSize(root);
    }
    
    
    /**
     * add the point p to the set (if it is not already in the set)
     * @param p point
     */
    public void insert(Point2D p) {
        root = insert(root, p, HORIZONTAL, null);
    }
    
    private int getSize(Node node) {
        if (node == null) {
            return 0;
        }
        
        return node.size;
    }
    
    private Node insert(Node node, Point2D p, boolean type, Node parent) {
        
        if (node == null) {
            
            RectHV r;
            if (parent == null) {
                r = new RectHV(0.0, 0.0, 1.0, 1.0);
            } else {
                r = parent.getRect(p);
            }
            
            return new Node(p, type, r);
        }

        if (node.compareTo(p) > 0) {
            node.left = insert(node.left, p, !type, node);
        } else if (node.compareTo(p) < 0) {
            node.right = insert(node.right, p, !type, node);
        } else {
            node.value = p;
        }
        
        node.size = 1 + getSize(node.left) + getSize(node.right);
        
        return node;
    }
    
    /**
     * @param p point
     * @return does the set contain the point p?
     */
    public boolean contains(Point2D p) {
        return search(root, p) != null;
    }
    
    private Node search(Node node, Point2D p) {
        if (node == null) {
            return null;
        }
        
        if (node.compareTo(p) > 0) {
            return search(node.left, p);
        } else if (node.compareTo(p) < 0) {
            return search(node.right, p);
        }
        
        return node;
    }
    
    /**
     * draw all of the points to standard draw
     */
    public void draw() {
        // draw canvas border
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(0, 0, 1, 0);
        StdDraw.line(1, 0, 1, 1);
        StdDraw.line(1, 1, 0, 1);
        StdDraw.line(0, 1, 0, 0);
        
        draw(root, null);
    }
    
    private void draw(Node node, Node parent) {
        if (node == null) {
            return;
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        node.value.draw();
        
        double x = node.value.x();
        double y = node.value.y();
        
        if (node.type == HORIZONTAL) {
            StdDraw.setPenColor(StdDraw.RED);
            
            StdDraw.line(x, node.rect.ymin(), x, node.rect.ymax());
            
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            
            StdDraw.line(node.rect.xmin(), y, node.rect.xmax(), y);
        }
        
        draw(node.left, node);
        draw(node.right, node);
    }
    
    /**
     * @param rect rectangle
     * @return all points in the set that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> stack = new Stack<Point2D>();
        
        range(root, rect, stack);
        
        return stack;
    }
    
    private void range(Node node, RectHV rect, Stack<Point2D> stack) {
        
        if (node == null) {
            return;
        }
        
        if (node.compareTo(rect) > 0) {
            range(node.left, rect, stack);
        } else if (node.compareTo(rect) < 0) {
            range(node.right, rect, stack);
        } else {
            
            // both
            
            if (rect.contains(node.value)) {
                stack.push(node.value);
            }
            
            range(node.left, rect, stack);
            range(node.right, rect, stack);
        }
    }
    
    /**
     * @param p point
     * @return a nearest neighbor in the set to p; null if set is empty
     */
    public Point2D nearest(Point2D p) {
        minPoint = root.value;
        minDist = minPoint.distanceSquaredTo(p);
        
        searchNearest(root, p);
        return minPoint;
    }

    
    private void searchNearest(Node node, Point2D p) {
        double dist = node.value.distanceSquaredTo(p);

        if (minDist > dist) {
            minPoint = node.value;
            minDist = dist;
        }
        
        if (node.left != null && node.right != null) {
            double left, right;
            
            left = node.left.rect.distanceSquaredTo(p);
            right = node.right.rect.distanceSquaredTo(p);
            
            if (left < right) {
                searchNearest(node.left, p);
                
                if (right < minDist) {
                    searchNearest(node.right, p);
                }
                
            } else {
                searchNearest(node.right, p);
                
                if (left < minDist) {
                    searchNearest(node.left, p);
                }
            }
            
            return;
        }

        if (node.left != null) {
            if (node.left.rect.distanceSquaredTo(p) < minDist) {
                searchNearest(node.left, p);
            }
        }
        
        if (node.right != null) {
            if (node.right.rect.distanceSquaredTo(p) < minDist) {
                searchNearest(node.right, p);
            }
        }
        
        return;
    }
}
