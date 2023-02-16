/* Name: Jana Hayaly
* Pennkey: jhayaly
* Execution: java NBody
* Makes: Solar System Simulation
*/
public class NBody {
    public static void main(String[] args) {

        //initializing first variables
        double simulationTime = Double.parseDouble(args[0]);
        double timeStep = Double.parseDouble(args[1]);
        String fileName = args[2];

        //initializing inStream
        In inStream = new In(fileName);

        //declaring number of bodies and radius variables
        int numBodies = inStream.readInt();
        double radius = inStream.readDouble();

        //declaring arrays 
        double[] m = new double[numBodies];
        double[] px = new double[numBodies];
        double[] py = new double[numBodies];
        double[] vx = new double[numBodies];
        double[] vy = new double[numBodies];
        String[] img = new String[numBodies];

        //storing data
        for (int i = 0; i < numBodies; i++) {
            m[i]  = inStream.readDouble();
            px[i] = inStream.readDouble();
            py[i] = inStream.readDouble();
            vx[i] = inStream.readDouble();
            vy[i] = inStream.readDouble();
            img[i] = inStream.readString();
        }

        //turn on animation + loop
        PennDraw.enableAnimation(70);
        double timeElapsed = 0;
        PennDraw.setXscale(-radius, radius);
        PennDraw.setYscale(-radius, radius);

        while (timeElapsed < simulationTime) {

            //drawing background
            PennDraw.picture(0.5, 0.5, "starfield.jpg");
            
            //defining gravity
            double G = 6.67e-11;

            //velocity loop
            for (int i = 0; i < numBodies; i++) {

                double totalForceXForI = 0;
                double totalForceYForI = 0;

                for (int j = 0; j < numBodies; j++) {
                    //do NOT add force on itself
                    if (!(j == i)) {
                        //declare diff in x and y & d
                        double dX = px[j] - px[i];
                        double dY = py[j] - py[i];
                        double d = Math.sqrt(dX * dX + dY * dY);
                        //calculate F
                        double F = ((G * m[i]) / (d * d)) * (m[j]);
                        //calculate Fx and Fy
                        double fX = (F * dX) / d;
                        double fY = (F * dY) / d;
                        //update total force
                        totalForceXForI = totalForceXForI + fX;
                        totalForceYForI = totalForceYForI + fY;
                    } 
                }

                //calculate acceleration
                double aXForI = totalForceXForI / (m[i]);
                double aYForI = totalForceYForI / (m[i]);

                //calculating velocity- store by modifying double[] vx
                vx[i] = vx[i] + timeStep * aXForI;
                vy[i] = vy[i] + timeStep * aYForI;
            }

             //draw particles
            for (int i = 0; i < numBodies; i++) {
                px[i] = px[i] + (timeStep * vx[i]);
                py[i] = py[i] + (timeStep * vy[i]);
                PennDraw.picture(px[i], py[i], img[i]);
            }

            timeElapsed = timeElapsed + timeStep;
            PennDraw.advance();
        }
        System.out.printf("%d\n", numBodies);
        System.out.printf("%.2e\n", radius);
        for (int i = 0; i < numBodies; i++) {
            System.out.printf("%12.5e %12.5e %12.5e %12.5e %12.5e %12s\n", m[i], px[i], py[i], vx[i], vy[i], img[i]);
        }
    }
}