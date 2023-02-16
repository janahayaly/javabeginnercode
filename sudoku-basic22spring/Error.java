public interface Error {

//checking for any error
    public boolean check(Cell[][] initial, Cell input, 
    double xClick, double yClick);

//visualizing any error
    public void visualize(int row, int column);

}