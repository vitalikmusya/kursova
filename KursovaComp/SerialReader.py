import requests
import serial

ser = serial.Serial('COM3', 9600, timeout=1)
data = {"altitudeValue": None, "temperatureValue": None, "pressureValue": None, "relativePressureValue": None}

while 1:
    serial_data = ser.read(1).decode("ascii")
    if len(serial_data) > 0:
        if serial_data[0] == "A":  # altitude
            print(serial_data)
            serial_data = ser.read(3).decode("ascii")
            print(serial_data)
            data["altitudeValue"] = serial_data
        elif serial_data[0] == "T":  # temperature
            print(serial_data)
            serial_data = ser.read(4).decode("ascii")
            print(serial_data)
            data["temperatureValue"] = serial_data
        elif serial_data[0] == "P":  # pressure
            print(serial_data)
            serial_data = ser.read(6).decode("ascii")
            print(serial_data)
            data["pressureValue"] = serial_data
        elif serial_data[0] == "S":  # relative_pressure (sea-level)
            print(serial_data)
            serial_data = ser.read(6).decode("ascii")
            print(serial_data)
            data["relativePressureValue"] = serial_data
    if data["altitudeValue"] and data["temperatureValue"] and data["pressureValue"] and data["relativePressureValue"]:
        print(data)
        requests.post('http://localhost:8080/bmp_data', json=data)
        data["altitudeValue"] = None
        data["temperatureValue"] = None
        data["pressureValue"] = None
        data["relativePressureValue"] = None
