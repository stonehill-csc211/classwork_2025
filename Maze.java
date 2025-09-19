
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    int[][] rooms;
    final int START = 3;
    final int END = 4;

    Coordinate start;

    private class Coordinate{
        private Coordinate(int y, int x){
            this.y = y;
            this.x = x;
        }
        int x;
        int y;
    }

    public boolean isSolvableRecursive(){
        // set up visited

        return explore(start);
    }

    private boolean explore(Coordinate current){
        // return true if the solution is found from this cell
        // false otherwise
        visited[current.y][current.x] = true;

        if(rooms[current.y][current.x] == this.END)
            return true;
        
        if(rooms[current.y+1][current.x] != 1 
            && !visited[current.y+1][current.x]){
            explore(
                new Coordinate(current.y+1, current.x)
            );
            }
        if(rooms[current.y-1][current.x] != 1 
            && !visited[current.y-1][current.x]){
            explore(
                new Coordinate(current.y-1, current.x)
            );
            }
        if(rooms[current.y][current.x-1] != 1 
            && !visited[current.y][current.x-1]){
            explore(
                new Coordinate(current.y, current.x-1)
            );
            }
        if(rooms[current.y][current.x+1] != 1 
            && !visited[current.y][current.x+1]){
            explore(
                new Coordinate(current.y, current.x+1)
            );
            }
    }

    public boolean isSolvable(){
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];
        for(int i = 0; i < visited.length; i++){
            for(int j = 0; j < visited[i].length; j++){
                visited[i][j] = false;
            }
        }
        Stack<Coordinate> todolist = new LinkedStack<>();
        todolist.push(start);
        Coordinate current;
        while(todolist.size() > 0){
            current = todolist.pop();
            visited[current.y][current.x] = true;

            if(rooms[current.y][current.x] == this.END)
                return true;
            
            if(rooms[current.y+1][current.x] != 1 
               && !visited[current.y+1][current.x]){
                todolist.push(
                    new Coordinate(current.y+1, current.x)
                );
               }
            if(rooms[current.y-1][current.x] != 1 
               && !visited[current.y-1][current.x]){
                todolist.push(
                    new Coordinate(current.y-1, current.x)
                );
               }
            if(rooms[current.y][current.x-1] != 1 
               && !visited[current.y][current.x-1]){
                todolist.push(
                    new Coordinate(current.y, current.x-1)
                );
               }
            if(rooms[current.y][current.x+1] != 1 
               && !visited[current.y][current.x+1]){
                todolist.push(
                    new Coordinate(current.y, current.x+1)
                );
               }
        }
        return false;
    }
    

    public Maze(Scanner roomFile){
        String row;
        String[] rowValues;
        int rowNumber = 0;
        int rows = roomFile.nextInt();
        int columns = roomFile.nextInt();
        roomFile.nextLine();
        this.rooms = new int[rows][columns];
        while(roomFile.hasNextLine()){
            row = roomFile.nextLine();
            rowValues = row.split(" ");
            for(int i = 0; i < rowValues.length; i++){
                if(rowValues[i].equals("E")){
                    rooms[rowNumber][i] = END;
                } else if(rowValues[i].equals("S")){
                    rooms[rowNumber][i] = START;
                    start = new Coordinate(rowNumber, i);
                } else {
                    rooms[rowNumber][i] = Integer.parseInt(rowValues[i]);
                }
            }
            rowNumber++;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[i].length; j++){
                sb.append(rooms[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new File("maze.txt"));
            Maze m = new Maze(sc);
            System.out.println(m);
            System.out.println(m.isSolvable());
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
}
