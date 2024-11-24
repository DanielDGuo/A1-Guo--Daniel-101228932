const apiBaseUrl = "http://localhost:8080";

async function startGame() {
    try {
        const response = await fetch(`${apiBaseUrl}/startGame`);
        const result = await response.text();
        console.log("Start Game Response:", result);
        document.getElementById("output").innerText += "Game Started\n";

        //converts returned JSON string into an actual object
        var nextEventCard = await getEvent();
        var eventCardObj = JSON.parse(nextEventCard);

        console.log("Drawn Event Card: ", nextEventCard);
        document.getElementById("output").innerText += "Drawn event card: " + eventCardObj.id;


    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function getEvent() {
    try {
        const response = await fetch(`${apiBaseUrl}/drawEvent`, { method: "POST" });
        const result = await response.text();
        return result;
    } catch (error) {
        console.error("Error in event card draw:", error);
    }
}