/*
 * Treasure Chandler
 * CS 16000-01 - 03, Fall Semester 2024
 * Project 1: Compute the Projectile Motion
 * 
 * Description: The purpose of this Java class is to understand
 * the laws of physics and the fundamentals of projectile motion
 * witin a Java program.
 * 
 */

import javax.swing.JDialog; // needed for the JOptionPane class
import javax.swing.JOptionPane; // needed for the JOptionPane class

public class ProjectileMotion {
    /**
     * 
     * @param args      entered values
     */
    public static void main(String[] args) {
        // variables declaration
        /* 
         * note: the "final" keyword is used
         * for immutable variables, and ALL
         * CAPS are used for constant variables
         */
        /*
         * the immutable and constant variable for the
         * projectile's gravitation
         */
        final int GRAVITATION = 32;

        /*
         * the distance in feet of the projectile to
         * the desired target (input)
         */
        final double distanceToTarget;

        // the projectile's initial velocity in ft/sec (input)
        double initialVelocity;

        // the launch angle of the projectile in degrees (input)
        double launchAngle;

        // the value in radians of the angle (calculated)
        double radian;

        /* 
         * the projectile's time in flight as defined by formula 1
         * (calculated)
         */
        double flightTime;

        // the projectile's maximum point in flight (calculated)
        double highestPoint;
        
        /*
         * the projectile's distance traveled while in flight as
         * defined by formula 3 (calculated)
         */
        double distanceTraveled;

        /* 
         * the value of the difference between the distance travelled
         * and the distance to the target (calculated)
         */
        double error;

        /* 
         * the least absolute error; this updates every time a new error
         * value is generated (calculated)
         */
        double minError;

        // the current output message built upon the trajectory data
        String trajectory;

        /*
         * count the number of attempts, including the initial attempt as
         * the first attempt
         */
        int nosOfAttempts = 0;

        /*
         * a constant final variable to specify 5 as the maximum number
         * of attempts
         */
        final int MAX_NOS_OF_ATTEMPTS = 5;

        /* 
         * string variables meant to be used for the
         * dialog boxes
         */
        String range, task, title;

        /*
         * due to the JOptionPane dialogue boxes sometimes appearing behind
         * all of your windows, you will need to declare a JDialog and
         * setAlwaysOnTop to true
         */
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        // prompts the user to input the distance to the desired target
        task = "Enter distance to target in feet:";
        distanceToTarget = Double.parseDouble(JOptionPane.showInputDialog(dialog, task));

        // prompts the user to input the initial velocity in ft/s
        task = "Enter initial velocity in feet/sec:";
        initialVelocity = Double.parseDouble(JOptionPane.showInputDialog(dialog, task));

        // prompts the user to input the launch angle in degrees
        task = "Enter the launch angle in degrees." + 
               "\nThe initial input must be a 45 degree angle:";
        launchAngle = Double.parseDouble(JOptionPane.showInputDialog(dialog, task));

        // creates calculations to display in a new dialog window
        flightTime = (2 * initialVelocity * Math.sin((launchAngle * Math.PI) / 180) 
                     / GRAVITATION);
        highestPoint = (initialVelocity * Math.sin((launchAngle * Math.PI) / 180) 
                       * (flightTime / 2) - (.5 * GRAVITATION * ((flightTime/2) 
                       * (flightTime / 2))));
        distanceTraveled = (initialVelocity * Math.cos((launchAngle * Math.PI) / 180)
                           * flightTime);
        error = distanceTraveled - distanceToTarget;

        trajectoryDataReport(initialVelocity, launchAngle, flightTime, 
                             highestPoint, distanceTraveled, error);

        // nosOfAttempts = 1;
        // while (nosOfAttempts < MAX_NOS_OF_ATTEMPTS) {
        //     nosOfAttempts ++;
        // } // end of while (nosOfAttempts < MAX_NOS_OF_ATTEMPTS)

        System.exit(0);
    } // end of main()

    /**
     * 
     * @param initialVelocity     // projectile's initial velocity (ft/s)  
     * @param launchAngle         // current input for launch angle (degrees)
     * @param flightTime          // flight time of the projectile (seconds)
     * @param highestPoint        // maximum height of the projectile (feet)
     * @param distanceTraveled    // distance travelled byt he projectile (feet)
     * @param error               // difference between distance traveled and
     *                            // distance to the target (feet)
     */
    public static void trajectoryDataReport(double initialVelocity,
                                            double launchAngle,
                                            double flightTime,
                                            double highestPoint,
                                            double distanceTraveled,
                                            double error) {
        /*
         * due to the JOptionPane dialogue boxes sometimes appearing behind
         * all of your windows, you will need to declare a JDialog and
         * setAlwaysOnTop to true
         */
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        // prints calculations in the window
        String title = "Trajectory Data Report";
        String range =  String.format("Initial velocity: %.2f ft/s\n" +
                                      "Launch angle: %.2f degrees\n" +
                                      "Flight time: %.2f seconds\n" +
                                      "Maximum height: %.2f feet\n" +
                                      "Distance traveled: %.2f feet\n" +
                                      "Target missed by: %.2f feet\n",
                                      initialVelocity, launchAngle,
                                      flightTime, highestPoint,
                                      distanceTraveled, error);
        JOptionPane.showMessageDialog(dialog, range, title,
                                      JOptionPane.INFORMATION_MESSAGE);

    }

    // public static double reenterLaunchAngle(int nosOfAttempts) {
    //     String task = "";
    //     String title = "";
    //     /*
    //      * to record the number of attempts of input for the launch
    //      * angle
    //      */
    //     String attempt;

    //     if (nosOfAttempts == 1) attempt = "Inifial attempt";
    // } // end of reenterLaunchAngle(int nosOfAttempts)

} // end of ProjectileMotion