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

    // YOUR REAL RENDER BACKEND URL

    private static final String API_URL =
            "https://hms-backend-d9yo.onrender.com/patients";

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

            conn.setRequestProperty(
                    "Accept",
                    "application/json"
            );

            conn.setDoOutput(true);

            Gson gson =
                    new Gson();

            String json =
                    gson.toJson(patient);

            OutputStream os =
                    conn.getOutputStream();

            os.write(
                    json.getBytes("utf-8")
            );

            os.flush();

            os.close();

            int responseCode =
                    conn.getResponseCode();

            System.out.println(
                    "POST Response Code: "
                            + responseCode
            );

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream()
                            )
                    );

            String line;

            while ((line = br.readLine())
                    != null) {

                System.out.println(line);
            }

            br.close();

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

            conn.setRequestProperty(
                    "Accept",
                    "application/json"
            );

            int responseCode =
                    conn.getResponseCode();

            System.out.println(
                    "GET Response Code: "
                            + responseCode
            );

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

            System.out.println(
                    response.toString()
            );

            Gson gson =
                    new Gson();

            // FULL RESPONSE OBJECT

            JsonObject jsonObject =
                    gson.fromJson(
                            response.toString(),
                            JsonObject.class
                    );

            // EXTRACT "data"

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

            patients.addAll(patientList);

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

            conn.setRequestProperty(
                    "Accept",
                    "application/json"
            );

            int responseCode =
                    conn.getResponseCode();

            System.out.println(
                    "PUT Response Code: "
                            + responseCode
            );

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream()
                            )
                    );

            String line;

            while ((line = br.readLine())
                    != null) {

                System.out.println(line);
            }

            br.close();

            conn.disconnect();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}