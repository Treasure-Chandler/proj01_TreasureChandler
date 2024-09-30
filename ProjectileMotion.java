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
        int nosOfAttempts = 1;

        /*
         * a constant final variable to specify 5 as the maximum number
         * of attempts
         */
        final int MAX_NOS_OF_ATTEMPTS = 5;

        /*
         * to record the number of attempts of input for the launch
         * angle
         */
        String attempt = "Intial Attempt";

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
        // checks for the user's input regarding the initial velocity
        if (initialVelocity < 210) {
            JOptionPane.showMessageDialog(dialog,
                                          "Target is too far!\n" +
                                          "Restart the program with greater " +
                                          "initial velocity!", 
                                          "Modification Needed (Initial Attempt)",
                                          JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // prompts the user to input the launch angle in degrees
        task = "Enter the launch angle in degrees." + 
               "\nThe initial input must be a 45 degree angle:";
        launchAngle = Double.parseDouble(JOptionPane.showInputDialog(dialog, task));
        nosOfAttempts += 1;
        // checks for the user's input regarding the launch angle
        if (launchAngle < 45) {
            JOptionPane.showMessageDialog(dialog,
                                          "Failed to enter 45 degrees on " +
                                          "the 1st attempt.\n" +
                                          "Restart the program with the " +
                                          "initial launch angle of 45 degrees!",
                                          "Modification Needed (Initial Attempt)",
                                          JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // creates calculations to display in a new dialog window
        radian = (launchAngle * Math.PI) / 180;
        flightTime = (2 * initialVelocity * Math.sin(radian) 
                     / GRAVITATION);
        highestPoint = (initialVelocity * Math.sin(radian) 
                       * (flightTime / 2) - (.5 * GRAVITATION * ((flightTime/2) 
                       * (flightTime / 2))));
        distanceTraveled = (initialVelocity * Math.cos(radian)
                           * flightTime);
        error = distanceTraveled - distanceToTarget;

        trajectoryDataReport(initialVelocity, launchAngle, flightTime, 
                             highestPoint, distanceTraveled, error);

        // checks if the target was missed or not
        if (error < 1 && error > -1) {
            JOptionPane.showMessageDialog(dialog,
                                          "The target is hit within an " +
                                          " error of 1 foot!" +
                                          "\nThe program terminates.",
                                          "Missed Target",
                                          JOptionPane.ERROR_MESSAGE);
            System.out.println(String.format("Your best shot missed the " +
                                             "target with %.2f feet", error));
            System.exit(0);
        }

        while (nosOfAttempts < MAX_NOS_OF_ATTEMPTS) {
            // enter the launch angle once again
            launchAngle = reenterLaunchAngle(nosOfAttempts);

            // re-calculate all trajectory data
            radian = (launchAngle * Math.PI) / 180;
            flightTime = (2 * initialVelocity * Math.sin(radian) 
                          / GRAVITATION);
            highestPoint = (initialVelocity * Math.sin(radian) 
                            * (flightTime / 2) - (.5 * GRAVITATION * ((flightTime/2) 
                            * (flightTime / 2))));
            distanceTraveled = (initialVelocity * Math.cos(radian)
                                * flightTime);
            error = distanceTraveled - distanceToTarget;

            // re-calculate the error and update the minumum error
            error = distanceTraveled - distanceToTarget;
            minError = distanceTraveled - distanceToTarget;
            minError = Math.min(Math.abs(error), Math.abs(error));

            // calls trajectoryDataReport()
            trajectoryDataReport(initialVelocity, launchAngle, flightTime, 
                                 highestPoint, distanceTraveled, error);

            // reinforce the initial attempt with a launch angle of 45 degrees
            if (launchAngle < 45) {
                JOptionPane.showMessageDialog(dialog,
                                              "Failed to enter 45 degrees on " +
                                              "the 1st attempt.\n" +
                                              "Restart the program with the " +
                                              "initial launch angle of 45 degrees!",
                                              "Modification Needed (Initial Attempt)",
                                              JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            // calls errorAnalysis()
            errorAnalysis(attempt, error, minError, launchAngle);

            // update the number of attempts
            nosOfAttempts++;
        } // end of while (nosOfAttempts < MAX_NOS_OF_ATTEMPTS)

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

    /**
     * 
     * @param nosOfAttempts     // tracks the number of attempts for each set of inputs
     * @return                  // returns the launch angle
     */
    public static double reenterLaunchAngle(int nosOfAttempts) {
        // variables to modify the text in the JOptionPane dialogue boxes
        String task = "Enter the launch angle in degrees:";
        String title;
        @SuppressWarnings("unused")
        double launchAngle;
        /*
         * due to the JOptionPane dialogue boxes sometimes appearing behind
         * all of your windows, you will need to declare a JDialog and
         * setAlwaysOnTop to true
         */
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        // checks for the number of attempts
        if (nosOfAttempts == 2) {
            title = "Modified Input (2nd Attempt)";
            return launchAngle = Double.parseDouble(JOptionPane.showInputDialog(dialog,
                                                                                task,
                                                                                title,
                                                                                JOptionPane.QUESTION_MESSAGE));
        } else if (nosOfAttempts == 3) {
            title = "Modified Input (3rd Attempt)";
            return launchAngle = Double.parseDouble(JOptionPane.showInputDialog(dialog,
                                                                                task,
                                                                                title,
                                                                                JOptionPane.QUESTION_MESSAGE));
        } else if (nosOfAttempts == 4) {
            title = "Modified Input (4th Attempt)";
            return launchAngle = Double.parseDouble(JOptionPane.showInputDialog(dialog,
                                                                                task,
                                                                                title,
                                                                                JOptionPane.QUESTION_MESSAGE));
        } else {
            title = "Modified Input (5th Attempt)";
            return launchAngle = Double.parseDouble(JOptionPane.showInputDialog(dialog,
                                                                                task,
                                                                                title,
                                                                                JOptionPane.QUESTION_MESSAGE));
        } // end of if statements
    } // end of reenterLaunchAngle(int nosOfAttempts)

    /**
     * 
     * @param attempt       // number of attempts recorded
     * @param error         // difference between distance travelled and 
     *                      // distance to the target (feet)
     * @param minError      // stores the least absolute error
     * @param launchAngle   // current input for launch angle (degrees)
     */
    public static void errorAnalysis(String attempt, double error,
                                     double minError, double launchAngle) {
        /*
         * due to the JOptionPane dialogue boxes sometimes appearing behind
         * all of your windows, you will need to declare a JDialog and
         * setAlwaysOnTop to true
         */
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        String shotBeyond = "Shot went beyond the target. " +
                            "Decrease the launch angle from ";
        String fellShort = "Shot fell short of the target. " +
                           "Increase the launch angle from ";
        if (error < 1 && error > -1) {
        JOptionPane.showMessageDialog(dialog,
                                     "The target is hit within an " +
                                     " error of 1 foot!" +
                                     "\nThe program terminates.",
                                     "Missed Target",
                                     JOptionPane.ERROR_MESSAGE);
        System.out.println(String.format("Your best shot missed the " +
                                        "target with %.2f feet", error));
         System.exit(0);
        } else if (error < -1) {
            System.out.println(attempt + "\n" + fellShort + launchAngle + "!");
        } else {
            System.out.println(attempt + "\n" + shotBeyond + launchAngle + "!");
        }
    } // end of errorAnalysis(String attempt, double error, double minError, double launchAngle)

} // end of ProjectileMotion