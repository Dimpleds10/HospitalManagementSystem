package service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Patient;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

public class HospitalService {

    private static final String API_URL =
            "http://localhost:8080/patients";

    // ADD PATIENT

    public static void addPatient(
            Patient patient
    ) {

        try {

            URL url =
                    new URL(API_URL);

            HttpURLConnection conn =
                    (HttpURLConnection)
                            url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty(
                    "Content-Type",
                    "application/json"
            );

            conn.setDoOutput(true);

            Gson gson =
                    new Gson();

            String json =
                    gson.toJson(patient);

            OutputStream os =
                    conn.getOutputStream();

            os.write(json.getBytes());

            os.flush();

            os.close();

            int responseCode =
                    conn.getResponseCode();

            System.out.println(
                    "POST Response: "
                            + responseCode
            );

            conn.disconnect();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // GET ALL PATIENTS

    public static ObservableList<Patient>
    getPatients() {

        ObservableList<Patient> patients =
                FXCollections.observableArrayList();

        try {

            URL url =
                    new URL(API_URL);

            HttpURLConnection conn =
                    (HttpURLConnection)
                            url.openConnection();

            conn.setRequestMethod("GET");

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream()
                            )
                    );

            StringBuilder response =
                    new StringBuilder();

            String line;

            while ((line = br.readLine())
                    != null) {

                response.append(line);
            }

            br.close();

            Gson gson =
                    new Gson();

            // PARSE ROOT OBJECT

            JsonObject jsonObject =
                    gson.fromJson(
                            response.toString(),
                            JsonObject.class
                    );

            // GET "data" ARRAY

            JsonArray dataArray =
                    jsonObject.getAsJsonArray(
                            "data"
                    );

            Type patientListType =
                    new TypeToken<
                            List<Patient>>() {
                    }.getType();

            List<Patient> patientList =
                    gson.fromJson(
                            dataArray,
                            patientListType
                    );

            patients =
                    FXCollections
                            .observableArrayList(
                                    patientList
                            );

            conn.disconnect();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return patients;
    }

    // DISCHARGE PATIENT

    public static void dischargePatient(
            Patient patient
    ) {

        try {

            URL url =
                    new URL(
                            API_URL
                                    + "/discharge/"
                                    + patient.getId()
                    );

            HttpURLConnection conn =
                    (HttpURLConnection)
                            url.openConnection();

            conn.setRequestMethod("PUT");

            int responseCode =
                    conn.getResponseCode();

            System.out.println(
                    "PUT Response: "
                            + responseCode
            );

            conn.disconnect();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // TOTAL ROOMS

    public static int getTotalRooms() {

        return 50;
    }

    // OCCUPIED ROOMS

    public static int getOccupiedRooms() {

        int count = 0;

        ObservableList<Patient> patients =
                getPatients();

        for (Patient p : patients) {

            if (p.getStatus() != null
                    && p.getStatus()
                    .equals("Admitted")) {

                count++;
            }
        }

        return count;
    }

    // AVAILABLE ROOMS

    public static int getAvailableRooms() {

        return getTotalRooms()
                - getOccupiedRooms();
    }

    // TOTAL REVENUE

    public static double getTotalRevenue() {

        double revenue = 0;

        ObservableList<Patient> patients =
                getPatients();

        for (Patient p : patients) {

            revenue += p.getFinalBill();
        }

        return revenue;
    }
}