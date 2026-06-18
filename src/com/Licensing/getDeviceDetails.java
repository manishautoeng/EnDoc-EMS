package com.Licensing;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Manish Pandey
 */
public class getDeviceDetails {

    public String fetchDetails() {

        try {
            // Get the network interface of the PC
            InetAddress ip = InetAddress.getLocalHost();
            String SystemDetail = ip.toString();
            System.out.println(SystemDetail);
            String inputString = SystemDetail;

            // Find the index of the first '/'
            int indexOfSlash = inputString.indexOf('/');

            // Extract the substring up to the first '/'
            String result = (indexOfSlash != -1) ? inputString.substring(0, indexOfSlash) : inputString;
            System.out.println(result);
            //This application only runs on these systems..... 

            return result;

        } catch (UnknownHostException e) {
        }
        //new homeScreen().setVisible(true);

        return "None";

    }

}
