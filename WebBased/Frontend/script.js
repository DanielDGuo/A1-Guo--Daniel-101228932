const apiBaseUrl = "http://localhost:8080";

const textInput = document.getElementById('textInput');
const submitButton = document.getElementById('submitButton');

//event listener to help with manual information entry
document.addEventListener('keydown', function(event){
    if (event.key.length === 1 && !event.ctrlKey && !event.metaKey) {
        textInput.focus();
    }
    else if (event.key === 'Enter') {
        submitButton.click();
    }
});

async function submitInput() {
    var userInput = textInput.value;
    if(userInput == ""){
        userInput = "empty_string";
    }
    console.log("Submitted input: ", userInput);
    console.log("With type: ", typeof userInput);
    textInput.value = '';

    // Send input to backend
    const response = await fetch(`${apiBaseUrl}/inputProcessing`, {method: 'POST', headers: {'Content-Type': 'text/plain'}, body: userInput});
    const output = await response.text();
    document.getElementById("output").innerText += output;
    document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
}

async function startGame() {
    try {
        //clear any janky javascript that I coded, i.e prosperity effect
        await fetch(`${apiBaseUrl}/startGame`);
        console.log("Started Game");
        document.getElementById("output").innerText = "Game Started\n";

        while(await findWinners() == 'false'){
            await nextPlayerTurn();
            var eventCard = await getEvent();

            switch (eventCard.id) {
                case "Plague":
                    await plagueEffect();
                    break;
                case "Queen's Favour":
                    document.getElementById("output").innerText += "Queen's Favour Drawn. Current player draws 2 cards.\n";
                    document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
                    await queenEffect();
                    break;
                case "Prosperity":
                    document.getElementById("output").innerText += "Prosperity Drawn. Each player draws 2 cards.\n";
                    document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
                    await prosperityEffect();
                    break;
            }
            if (eventCard.type == "Quest"){
                //quest logic here
            }
        }
        document.getElementById("output").innerText += await printWinners();

    } catch (error) {
        console.error("Error in startGame:", error);
    }
}

async function getEvent() {
    try {
        const response = await fetch(`${apiBaseUrl}/drawEvent`, { method: "POST" });
        const result = await response.text();

        var eventCardObj = JSON.parse(result);
        //extract the message and the card JSON
        const [key, value] = Object.entries(eventCardObj)[0];

        console.log("Drawn Event Card: ", result);
        document.getElementById("output").innerText += key;
        document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
        return value;
    } catch (error) {
        console.error("Error in event card draw:", error);
    }
}

async function getGamePhase(){
    try {
        const response = await fetch(`${apiBaseUrl}/getGamePhase`);
        const result = await response.text();

        console.log("Current Game Phase: ", result);
        return result;
    } catch (error) {
        console.error("Error in event card draw:", error);
    }
}

async function findWinners(){
    try {
        const response = await fetch(`${apiBaseUrl}/findWinners`);
        const result = await response.text();
        return result;
    } catch (error) {
        console.error("Error in finding winners:", error);
    }
}

async function printWinners(){
    try {
        const response = await fetch(`${apiBaseUrl}/printWinners`);
        const result = await response.text();
        return result;
    } catch (error) {
        console.error("Error in printing winners:", error);
    }
}

async function nextPlayerTurn(){
    try {
        const response = await fetch(`${apiBaseUrl}/nextPlayer`, { method: "POST" });
        const result = await response.text();

        console.log("Next Turn Output", result);
        document.getElementById("output").innerText += result;
        document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
    } catch (error) {
        console.error("Error in getting next turn", error);
    }
}

async function endTurn(){
    try {
        const response = await fetch(`${apiBaseUrl}/endTurn`, { method: "POST" });
        const result = await response.text();

        console.log("End Turn Output", result);
        document.getElementById("output").innerText += result;
        document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
    } catch (error) {
        console.error("Error in getting next turn", error);
    }
}

async function plagueEffect(){
    try {
        const response = await fetch(`${apiBaseUrl}/plagueEffect`, { method: "POST" });
        const result = await response.text();

        console.log("Plague Effect", result);
        document.getElementById("output").innerText += result;
        document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
    } catch (error) {
        console.error("Error in getting plague effect", error);
    }
}

async function queenEffect(){
    try {
        const response = await fetch(`${apiBaseUrl}/queenEffect`, { method: "POST" });
        const result = await response.text();

        console.log("Queen Effect");
        document.getElementById("output").innerText += result;
        document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(await getGamePhase() != ""){
        }
    } catch (error) {
        console.error("Error in getting queen effect", error);
    }
}

async function prosperityEffect(){
    try {
        var response = await fetch(`${apiBaseUrl}/prosperityEffect1`, { method: "POST" });
        var result = await response.text();

        console.log("Prosperity Effect1");
        document.getElementById("output").innerText += result;
        document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(await getGamePhase() != ""){
        }

        if(await getGamePhase() == "New Game"){
            return;
        }
        response = await fetch(`${apiBaseUrl}/prosperityEffect2`, { method: "POST" });
        result = await response.text();

        console.log("Prosperity Effect2");
        document.getElementById("output").innerText += result;
        document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(await getGamePhase() != ""){
        }

        if(await getGamePhase() == "New Game"){
            return;
        }
        response = await fetch(`${apiBaseUrl}/prosperityEffect3`, { method: "POST" });
        result = await response.text();

        console.log("Prosperity Effect3");
        document.getElementById("output").innerText += result;
        document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(await getGamePhase() != ""){
        }

        if(await getGamePhase() == "New Game"){
            return;
        }
        response = await fetch(`${apiBaseUrl}/prosperityEffect4`, { method: "POST" });
        result = await response.text();

        console.log("Prosperity Effect4");
        document.getElementById("output").innerText += result;
        document.getElementById("output").scrollTop = document.getElementById("output").scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(await getGamePhase() != ""){
        }
    } catch (error) {
        console.error("Error in prosperity", error);
    }
}

