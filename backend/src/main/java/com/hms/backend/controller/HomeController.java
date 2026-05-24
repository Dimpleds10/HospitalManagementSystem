package com.hms.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

    @GetMapping("/")

    public String home() {

        return """
                
                <html>
                
                <head>
                
                    <title>Hospital Management System</title>
                
                    <style>
                    
                        body {
                            font-family: Arial;
                            background: #f4f7fc;
                            padding: 40px;
                        }
                        
                        .container {
                            background: white;
                            padding: 30px;
                            border-radius: 12px;
                            box-shadow: 0 0 10px rgba(0,0,0,0.1);
                        }
                        
                        h1 {
                            color: #2563eb;
                        }
                        
                        a {
                            text-decoration: none;
                            color: #2563eb;
                            font-weight: bold;
                        }
                        
                    </style>
                
                </head>
                
                <body>
                
                    <div class="container">
                    
                        <h1>
                            Hospital Management System API
                        </h1>
                        
                        <h3>
                            Backend Status: RUNNING
                        </h3>
                        
                        <h2>
                            Available APIs
                        </h2>
                        
                        <ul>
                        
                            <li>GET /patients</li>
                            
                            <li>POST /patients</li>
                            
                            <li>PUT /patients/discharge/{id}</li>
                        
                        </ul>
                        
                        <h2>
                            Patients API Link
                        </h2>
                        
                        <a href="/patients">
                            Open Patients API
                        </a>
                    
                    </div>
                
                </body>
                
                </html>
                """;
    }
}