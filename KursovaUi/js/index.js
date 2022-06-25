const URL = 'http://localhost:8080/bmp_data';

const getPressure = async () => {
    const response = await fetch(URL);
    const data = await response.json();
    console.log(data)
    const length = data.length-1;
    console.log(length)
    let pressureValue = data[length].pressureValue;
    document.getElementById("pressure").innerHTML = `${pressureValue} Pa`
    console.log(data[length])

};

const getAltitude = async () => {
        const response = await fetch(URL);
        const data = await response.json();
        console.log(data)
        const length = data.length-1;
        console.log(length)
        let altitudeValue = data[length].altitudeValue;
        document.getElementById("altitude").innerHTML =`${altitudeValue} m`
        console.log(data[length])
};

const getTemperature = async () => {
        const response = await fetch(URL);
        const data = await response.json();
        console.log(data)
        const length = data.length-1;
        console.log(length)
        let temperatureValue = data[length].temperatureValue;
        document.getElementById("temperature").innerHTML = `${temperatureValue} °C ` 
        console.log(data[length])
};

const getRelativePressure = async () => {
        const response = await fetch(URL);
        const data = await response.json();
        console.log(data)
        const length = data.length-1;
        console.log(length)
        let relativePressureValue = data[length].relativePressureValue;
        document.getElementById("relativePressure").innerHTML = `${relativePressureValue} Pa`
        console.log(data[length])
};


window.onload = setInterval(getPressure,8000);
window.onload = setInterval(getAltitude, 8000);
window.onload = setInterval(getTemperature,8000);
window.onload = setInterval(getRelativePressure, 8000);
