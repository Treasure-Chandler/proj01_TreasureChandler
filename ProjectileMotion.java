/*
 * Treasure Chandler
 * CS 16000-01 - 03, Fall Semester 2024
 * Project 1: Compute the Projectile Motion
 * 
 * Description: The purpose of this class is to understand the
 * laws of physics and the fundamentals of projectile motion
 * witin a Java program.
 * 
 */

import javax.swing.JOptionPane; // needed for the JOptionPane class

public class ProjectileMotion {
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        // variables declaration
        /* the "final" keyword is used for
         * immutable variables, and ALL CAPS
         * are used for constant variables
         */

        /* the immutable and constant variable for the
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
         * dialogue boxes
         */
        String task, task1, title;

        nosOfAttempts = 1;
        while (nosOfAttempts < MAX_NOS_OF_ATTEMPTS) {
            nosOfAttempts ++;
        } // end of while (nosOfAttempts < MAX_NOS_OF_ATTEMPTS)

        System.exit(0);
    } // end of main()

    // public static double reenterLaunchAngle(int nosOfAttempts){
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
