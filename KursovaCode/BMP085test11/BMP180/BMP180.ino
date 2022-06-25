#include <LiquidCrystal.h>
LiquidCrystal lcd(13, 12, 11, 10, 9, 8);//RS,EN,D4,D5,D6,D7
#include <SFE_BMP180.h>
#include <Wire.h>

SFE_BMP180 pressure; //create an SFE_BMP180 object called a "pressure"
#define ALTITUDE 296 // Altitude of LVIV location in meters
void setup() {
  Serial.begin(9600);
  lcd.begin(20, 4);
  lcd.setCursor(0, 0);
  lcd.print("BMP180 Started:"); //LCD Print
  lcd.setCursor(0, 1);
  lcd.print("BMP180 is Preassure");
  lcd.setCursor(0, 2);
  lcd.print("sensor including");
  lcd.setCursor(0, 3);
  lcd.print("hPa, Temp, altitude");
  delay (2000);
  lcd.clear();//clear display
  pressure.begin();
}
void loop() {

  char status;
  char buff[8] = {0};
  double Temperature, Pressure, RelativePressure;

  lcd.setCursor(0, 0);
  lcd.print("Altitude: "); //scnd frame on LCD
  Serial.write("A");
  //  sprintf(buff, "%05d", ALTITUDE);
  //  Serial.write(buff);
  String alti = String(ALTITUDE);
  alti.toCharArray(buff, alti.length()+ 1);

  for (int i = 0 ; i < alti.length(); i++ ) {
    Serial.write(buff[i]);
  }
  Serial.println();
  lcd.print(ALTITUDE, DEC);
  lcd.print(" m");

  // Start a temperature measurement:
  status = pressure.startTemperature();
  if (status != 0) {
    delay(status);
    status = pressure.getTemperature(Temperature);
    if (status != 0) {
      Serial.write("T");
      //      sprintf(buff, "%2.2f", Temp);
      String temp = String(Temperature);
      temp.toCharArray(buff, temp.length());

      for (int i = 0 ; i < temp.length(); i++ ) {
        Serial.write(buff[i]);
      }
      Serial.println();
      lcd.setCursor(0, 1);
      lcd.print("Temperature: ");
      lcd.print(Temperature, DEC);
      lcd.print(" C ");
      
      // Start a pressure measurement:
      status = pressure.startPressure(3);
      if (status != 0) {
        delay(status);
        status = pressure.getPressure(Pressure, Temperature);
        if (status != 0) {

          Serial.write("P");
          String pres = String(Pressure);
          pres.toCharArray(buff, pres.length());

          for (int i = 0 ; i < pres.length(); i++ ) {
            Serial.write(buff[i]);
          }
          Serial.println();
          //          sprintf(buff, "%05d", Pa);
          //          Serial.write(buff);
          lcd.setCursor(0, 2);
          lcd.print("Abs. Pr: ");
          lcd.print(Pressure, DEC);
          lcd.print(" mb");
          
          // The pressure sensor returns abolute pressure, which varies with altitude.
          // Parameters: Pressure = absolute pressure in mb, ALTITUDE = current altitude in m.
          // Result: RelativePressure = sea-level compensated pressure in mb
          
          RelativePressure = pressure.sealevel(Pressure, ALTITUDE); // we are at 296 meters (LVIV)
          Serial.write("S");
          String rela = String(RelativePressure);
          rela.toCharArray(buff, rela.length());

          for (int i = 0 ; i < rela.length(); i++ ) {
            Serial.write(buff[i]);
          }
          Serial.println();

          //          sprintf(buff, "%05d", Pa0);
          //          Serial.write(buff); //"relative (sea-level) pressure: " unit:  mb
          lcd.setCursor(0, 3);
          lcd.print("Sealvl Pr: ");
          lcd.print(RelativePressure, DEC);
          lcd.print("mb");
        }

      }

    }

    delay(3000); // Mini brake for 5 seconds
  }
}
