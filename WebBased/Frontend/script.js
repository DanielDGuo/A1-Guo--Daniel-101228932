const apiBaseUrl = "http://localhost:8080";

async function startGame() {
    try {
        const response = await fetch(`${apiBaseUrl}/startGame`);
        const result = await response.text();
        console.log("Start Game Response:", result);
        document.getElementById("output").innerText += "Game Started\n";

        const eventResult = await getEvent();
    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function getEvent() {
    try {
        const response = await fetch(`${apiBaseUrl}/drawEvent`, { method: "POST" });
        result = await response.json();

        //console.log("Event Card Drawn:", result);
        document.getElementById("output").innerText += typeof result;
        document.getElementById("output").innerText += JSON.stringify(result, null, 2);

    } catch (error) {
        console.error("Error in event card draw:", error);
    }
}