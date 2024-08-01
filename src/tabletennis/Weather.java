/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tabletennis;

import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Weather extends javax.swing.JFrame {
    
    //private static final String API_URL = "https://geocoding-api.open-meteo.com/v1/search?name=Rome&count=1&language=en&format=json";

    /**
     * Creates new form Weather
     */
    public Weather() {
        initComponents();
        btnSearch.addActionListener(e -> fetchWeather());
    }
    
    private void fetchWeather() {
        String city = txfCity.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name.");
            return;
        }
        try {
            JSONObject cityLocationData = getLocationData(city);
            if (cityLocationData != null) {
                double latitude = (double) cityLocationData.get("latitude");
                double longitude = (double) cityLocationData.get("longitude");
                displayWeatherData(latitude, longitude);
            } else {
                txaDisplay.setText("Could not fetch location data.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching weather data: " + e.getMessage());
        }
    }

    private JSONObject getLocationData(String city) {
        city = city.replaceAll(" ", "+");
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
        try {
            HttpURLConnection apiConnection = fetchApiResponse(urlString);
            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }
            String jsonResponse = readApiResponse(apiConnection);
            JSONParser parser = new JSONParser();
            JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);
            JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
            return (JSONObject) locationData.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void displayWeatherData(double latitude, double longitude) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                    "&longitude=" + longitude + "&current_weather=true";
            HttpURLConnection apiConnection = fetchApiResponse(url);
            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return;
            }
            String jsonResponse = readApiResponse(apiConnection);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            JSONObject currentWeatherJson = (JSONObject) jsonObject.get("current_weather");

            if (currentWeatherJson == null) {
                txaDisplay.setText("No current weather data available.");
                return;
            }

            String displayText = "";
            Double temperature = (Double) currentWeatherJson.get("temperature");
            if (temperature != null) {
                displayText += String.format("Current Temperature (C): %.2f\n", temperature);
            } else {
                displayText += "Current Temperature (C): N/A\n";
            }

            Long relativeHumidity = (Long) currentWeatherJson.get("relative_humidity");
            if (relativeHumidity != null) {
                displayText += String.format("Relative Humidity: %d%%\n", relativeHumidity);
            } else {
                displayText += "Relative Humidity: N/A\n";
            }

            Double windSpeed = (Double) currentWeatherJson.get("windspeed");
            if (windSpeed != null) {
                displayText += String.format("Wind Speed: %.2f m/s\n", windSpeed);
            } else {
                displayText += "Wind Speed: N/A\n";
            }

            txaDisplay.setText(displayText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readApiResponse(HttpURLConnection apiConnection) {
        try {
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(apiConnection.getInputStream());
            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }
            scanner.close();
            return resultJson.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection fetchApiResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBackLogin = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDisplay = new javax.swing.JTextArea();
        txfCity = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblClouds = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Weather App");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBackLogin.setText("Back to login");
        btnBackLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnBackLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, -1, -1));

        btnSearch.setText("Search");
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        txaDisplay.setColumns(20);
        txaDisplay.setRows(5);
        jScrollPane1.setViewportView(txaDisplay);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 270, 90));
        getContentPane().add(txfCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 160, -1));

        lblTitle.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 24)); // NOI18N
        lblTitle.setText("Today's Weather");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        lblClouds.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tabletennis/TT7.jpg"))); // NOI18N
        getContentPane().add(lblClouds, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 370));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackLoginActionPerformed
        // TODO add your handling code here:
        //display form for administrator 
        new LogOnGUI().setVisible(true);
                    
        //make Log on form invisible 
        this.dispose();
    }//GEN-LAST:event_btnBackLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Weather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Weather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Weather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Weather.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Weather().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackLogin;
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClouds;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea txaDisplay;
    private javax.swing.JTextField txfCity;
    // End of variables declaration//GEN-END:variables
}
