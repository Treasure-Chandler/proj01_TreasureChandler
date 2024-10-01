/*
 * Treasure Chandler
 * CS 16000-01 - 03, Fall Semester 2024
 * Project 1: Compute the Projectile Motion
 * 
 * Description: The purpose of this Java class is to understand
 * the laws of physics and the fundamentals of projectile motion
 * witin a Java program. This class also simulates the process of
 * repeated attempts to hit a target with a projectile. The closest
 * distance one can get to hitting the target with said projectile is
 * 0.01 (or even 0) feet away from the target, which is considered a 
 * successful attempt.
 * 
 */

import javax.swing.JDialog; // needed for the JOptionPane class
import javax.swing.JOptionPane; // needed for the JOptionPane class

public class ProjectileMotion {
    /**
     * 
     * @param args      entered values
     */

    /*
     * in VSCode (or any IDE, VSCode is the one I use), there will be warnings
     * for some variables that have not been used (even though you can see
     * they are changed) throughout the program; this line is to suppress
     * those warnings
     */
    @SuppressWarnings("unused")
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
        double minError = 0;

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

        // records the number of attempts of input for the launch angle
        String attempt = "";

        /* 
         * string variables meant to be used for the
         * dialog boxes
         */
        String task, title;

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

        // prompts the user to input the initial velocity in ft/sec
        task = "Enter initial velocity in ft/sec:";
        initialVelocity = Double.parseDouble(JOptionPane.showInputDialog(dialog, task));
        // checks for the user's input regarding the initial velocity
        if (initialVelocity < 210) {
            JOptionPane.showMessageDialog(dialog,
                                          "Target is too far!\n" +
                                          "Restart the program with a greater " +
                                          "initial velocity!\n" +
                                          "Hint: The initial velocity should " +
                                          " be at least 210 ft/sec", 
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
        if (launchAngle < 45 || launchAngle > 45) {
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
                                          " error of 1 foot, which means" +
                                          "\nThe program will terminate.",
                                          "Successful Attempt",
                                          JOptionPane.ERROR_MESSAGE);
            System.out.println(String.format("Your best shot missed the " +
                                             "target by %.2f feet.", error));
            System.exit(0);
        }

        // emulates 4 more attempts in this while loop
        while (nosOfAttempts <= MAX_NOS_OF_ATTEMPTS) {
            // enter the launch angle again
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

            /*
             * tracks the attempts to change the text of the "attempt"
             * string
             */
            if (nosOfAttempts == 2) {
                attempt = "Second Attempt: ";
            } else if (nosOfAttempts == 3) {
                attempt = "Third Attempt: ";
            } else if (nosOfAttempts == 4) {
                attempt = "Fourth Attempt: ";
            } else if (nosOfAttempts >= 5 && (error < 1 || error > -1)) {
                attempt = "Fifth Attempt: ";
                errorAnalysis(attempt, error, minError, launchAngle);
                /*
                 * if all of the attempts are still unsuccessful, the
                 * error analysis will be run for the last time and
                 * these messages will print in the console
                 */
                System.out.println("End of the fifth attempt, which " +
                                   "was unsuccessful. Restart the " +
                                   "program and try again.\n" +
                                   String.format("Your best shot missed the " +
                                                 "target by %.2f feet.", error));
                System.exit(0);
            }

            // calls errorAnalysis()
            errorAnalysis(attempt, error, minError, launchAngle);

            // update the number of attempts
            nosOfAttempts++;

        } // end of while loop

        // needed for the JOptionPane class
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
        String trajectory =  String.format("Initial velocity: %.2f ft/sec\n" +
                                      "Launch angle: %.2f degrees\n" +
                                      "Flight time: %.2f seconds\n" +
                                      "Maximum height: %.2f feet\n" +
                                      "Distance traveled: %.2f feet\n" +
                                      "Target missed by: %.2f feet\n",
                                      initialVelocity, launchAngle,
                                      flightTime, highestPoint,
                                      distanceTraveled, error);
        JOptionPane.showMessageDialog(dialog, trajectory, title,
                                      JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * 
     * @param nosOfAttempts     // tracks the number of attempts for each set of inputs
     * @return                  // returns the launch angle
     */

    /*
     * in VSCode (or any IDE), there will be warnings for some variables
     * that have not been used (even though you can see they are changed)
     * throughout the program; this line is to suppress those warnings
     */
    @SuppressWarnings("unused")
    public static double reenterLaunchAngle(int nosOfAttempts) {
        // variables to modify the text in the JOptionPane dialogue boxes
        String task = "Enter the launch angle in degrees:";
        String title;
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
    public static void errorAnalysis(String attempt, double error, double minError,
                                     double launchAngle) {
        /*
         * due to the JOptionPane dialogue boxes sometimes appearing behind
         * all of your windows, you will need to declare a JDialog and
         * setAlwaysOnTop to true
         */
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        /*
         * variables are created in the event of shots going beyond/falling
         * short of the target
         */
        String shotBeyond = "Shot went beyond the target. " +
                            "Decrease the launch angle from ";
        String fellShort = "Shot fell short of the target. " +
                           "Increase the launch angle from ";

        /*
         * checks if the target was missed, or if the target
         * was too far or too short, along with printing the
         * best shot
         */
        if (error < 1 && error > -1) {
        JOptionPane.showMessageDialog(dialog,
                                     "The target is hit within an" +
                                     " error of 1 foot, which means " +
                                     "this was a sucessful attempt! " +
                                     "\nThe program will terminate.",
                                     "Successful Attempt",
                                     JOptionPane.INFORMATION_MESSAGE);
        System.out.println(String.format("Your best shot missed the " +
                                         "target by %.2f feet", error));
        System.exit(0);
        } else if (error < -1) {
            System.out.println(attempt + fellShort + launchAngle + "!");
        } else {
            System.out.println(attempt + shotBeyond + launchAngle + "!");
        } // end of if statements

    } // end of errorAnalysis(String attempt, double error, double minError, double launchAngle)

} // end of ProjectileMotion

/*
 * Data test with my own inputs of choice:
 * Distance to target: 800 feet
 * 
 * Initial attempt:
 * Initial velocity: 210 ft/sec
 * Launch angle: 45 degrees
 * Flight time: 9.28 seconds
 * Maximum height: 344.53 feet
 * Distance traveled: 1378.12 feet
 * Target missed by (error): 578.12 feet
 * 
 * Second attempt (after lots of testing, this
 * was the lowest possible error I could achieve):
 * Initial velocity: 210 ft/sec
 * Launch angle: 17.745 (rounded to 17.75) degrees
 * Flight time: 4 seconds
 * Maximum height: 64.01 feet
 * Distance traveled: 800.09 feet
 * Target missed by (error): 0.09 feet
 */