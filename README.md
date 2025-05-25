# 🚗 Park and Ride: Smart Parking & Last-Mile Connectivity Solution

This is a full-stack web application built with **Spring Boot** (Java) and **React.js**, designed to enhance urban mobility by integrating real-time parking reservations and last-mile transportation options (cabs, shuttles, and e-rickshaws). It helps commuters seamlessly pre-book parking slots, transition to public transport, and book last-mile rides for a hassle-free travel experience.

## 🔧 Tech Stack

- **Backend:** Java, Spring Boot, Spring Security, JWT Authentication, Hibernate/JPA
- **Frontend:** React.js, Axios, Bootstrap/Tailwind (Optional)
- **Database:** MySQL / PostgreSQL
- **Authentication:** JWT Token-based Authentication
- **Tools:** Maven, Postman, Swagger (for API docs)

---

## ✅ Core Features

### 1. 🚙 Seamless Parking Booking
- Book parking slots near metro stations.
- Reservation options: hourly, daily, monthly.
- Smart real-time parking spot assignment.
- QR Code / Digital Pass generation.
- LPR (License Plate Recognition) & RFID Entry ready.

### 2. 🚕 Last-Mile Ride Integration
- Book cabs, e-rickshaws, or shuttles from the metro.
- On-demand or scheduled rides.
- Pooling and route-optimized shared rides.
- Public transport schedule integration.

### 3. ⚖️ Conflict Resolution System
- Auto-cancellation of no-shows.
- Live availability tracking with dynamic slot reassignment.
- Prevents overbooking with real-time status updates.

### 4. 💸 Dynamic Pricing
- Pricing adapts based on time, demand, events.
- Peak hour surges and off-peak discounts.
- Subscription & loyalty rewards for regular users.

### 5. 📶 Offline Mode Support
- Access reservations offline.
- Preload parking maps & navigation.
- Auto-sync once internet is restored.



## 🚀 Getting Started

### Backend

1. **Setup DB:**  
   Configure your database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/park_ride
   spring.datasource.username=root
   spring.datasource.password=yourpassword



