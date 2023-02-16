/* Name: Jana Hayaly
* Pennkey: jhayaly
* Execution: java NBodyObj
* Does: Simulates planet rotations around the sun using object oriented programming
*/
public class NBodyObj {
    public static void main(String[] args) {

        //set up command-line arguments
        double simulationTime = Double.parseDouble(args[0]);
        double timeStep = Double.parseDouble(args[1]);
        String fileName = args[2];

        //create an instance of Space
        Space s = new Space(fileName);
        
        //enable animation and initialize time variable
        PennDraw.enableAnimation(24);
        double timeElapsed = 0;

        //time loop which draws and simulates planets
        while (timeElapsed < simulationTime) {
            s.draw();
            s.simulate(timeStep);

            timeElapsed = timeElapsed + timeStep;
            PennDraw.advance();
        }

        //turn off animation
        PennDraw.disableAnimation();

        //print final positions
        System.out.println(s);
    }
}
