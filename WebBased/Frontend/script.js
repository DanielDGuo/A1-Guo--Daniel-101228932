const apiBaseUrl = "http://localhost:8080";

const TEXT_INPUT = document.getElementById('textInput');
const SUBMIT_BUTTON = document.getElementById('submitButton');
const START_GAME_BUTTON = document.getElementById('startGameButton');
const OUTPUT_DIV = document.getElementById("output");

//event listener to help with manual information entry
document.addEventListener('keydown', function(event){
    if (event.key.length === 1 && !event.ctrlKey && !event.metaKey) {
        TEXT_INPUT.focus();
    }
    else if (event.key === 'Enter') {
        SUBMIT_BUTTON.click();
    }
});

async function submitInput() {
    var userInput = TEXT_INPUT.value;
    if(userInput == ""){
        userInput = "empty_string";
    }
    console.log("Submitted input: ", userInput);
    console.log("With type: ", typeof userInput);
    TEXT_INPUT.value = '';

    // Send input to backend
    const response = await fetch(`${apiBaseUrl}/inputProcessing`, {method: 'POST', headers: {'Content-Type': 'text/plain'}, body: userInput});
    const output = await response.text();
    console.log("Received output: ", output);
    console.log("With type: ", typeof output);
    OUTPUT_DIV.innerText += output;
    OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
}

async function startGame() {
    try {
        //disable the button to start a new game
        START_GAME_BUTTON.disabled = true;
        await fetch(`${apiBaseUrl}/startGame`);
        console.log("Started Game");
        OUTPUT_DIV.innerText = "Game Started\n";

        while(await findWinners() == 'false'){
            await nextPlayerTurn();
            var eventCard = await getEvent();

            switch (eventCard.id) {
                case "Plague":
                    await plagueEffect();
                    break;
                case "Queen's Favour":
                    OUTPUT_DIV.innerText += "Queen's Favour Drawn. Current player draws 2 cards.\n";
                    OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
                    await queenEffect();
                    break;
                case "Prosperity":
                    OUTPUT_DIV.innerText += "Prosperity Drawn. Each player draws 2 cards.\n";
                    OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
                    await prosperityEffect();
                    break;
            }
            if (eventCard.type == "Quest"){
                await seekSponsor();
                if (await getSponsor() == "no sponsor"){
                    console.log("No Sponsor Found")
                    await endTurn();
                    continue;
                }else{
                    console.log("Starting Quest Building")
                    await startQuestBuild();
                }
            }
            await endTurn();
        }
        if (await findWinners() == 'true' && await getGamePhase() != "New Game"){
            OUTPUT_DIV.innerText += await printWinners();
        }
        //re-enable the button to start a new game
        START_GAME_BUTTON.disabled = false;
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
        OUTPUT_DIV.innerText += key;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
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
        console.error("Error in getting phase:", error);
    }
}

async function getSponsor(){
    try {
        const response = await fetch(`${apiBaseUrl}/getSponsor`);
        const result = await response.text();

        console.log("Current Sponsor: ", result);
        return result;
    } catch (error) {
        console.error("Error in getting sponsor", error);
    }
}

async function findWinners(){
    try {
        const response = await fetch(`${apiBaseUrl}/findWinners`);
        const result = await response.text();
        console.log("winners checked: ", result);
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
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
    } catch (error) {
        console.error("Error in getting next turn", error);
    }
}

async function startQuestBuild(){
    try {
        const response = await fetch(`${apiBaseUrl}/startQuestBuild`, { method: "POST" });
        const result = await response.text();

        console.log("Build Phase Begin Output: ", result);
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;

        while(await getGamePhase() != "Stage Building"){
        }
    } catch (error) {
        console.error("Error in starting quest build", error);
    }
}

async function endTurn(){
    try {
        const response = await fetch(`${apiBaseUrl}/endTurn`, { method: "POST" });
        const result = await response.text();

        console.log("Ended Turn");
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
        while(await getGamePhase() != "New Turn"){
        }
        console.log("End Turn Output", result);
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
    } catch (error) {
        console.error("Error in ending turn", error);
    }
}

async function plagueEffect(){
    try {
        const response = await fetch(`${apiBaseUrl}/plagueEffect`, { method: "POST" });
        const result = await response.text();

        console.log("Plague Effect", result);
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
    } catch (error) {
        console.error("Error in getting plague effect", error);
    }
}

async function queenEffect(){
    try {
        const response = await fetch(`${apiBaseUrl}/queenEffect`, { method: "POST" });
        const result = await response.text();

        console.log("Queen Effect");
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
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
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(await getGamePhase() != ""){
        }
        response = await fetch(`${apiBaseUrl}/prosperityEffect2`, { method: "POST" });
        result = await response.text();

        console.log("Prosperity Effect2");
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(await getGamePhase() != ""){
        }
        response = await fetch(`${apiBaseUrl}/prosperityEffect3`, { method: "POST" });
        result = await response.text();

        console.log("Prosperity Effect3");
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(await getGamePhase() != ""){
        }
        response = await fetch(`${apiBaseUrl}/prosperityEffect4`, { method: "POST" });
        result = await response.text();

        console.log("Prosperity Effect4");
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(await getGamePhase() != ""){
        }
    } catch (error) {
        console.error("Error in prosperity", error);
    }
}

async function seekSponsor(){
    try {
        var response = await fetch(`${apiBaseUrl}/seekSponsor1`, { method: "POST" });
        var result = await response.text();

        console.log("Seek Sponsor Question 1: ", result);
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        var phase = await getGamePhase();
        while(phase != "Seek Sponsor 2" && phase != "Sponsor Search End"){
            phase = await getGamePhase();
        }
        if (await getGamePhase() == "Sponsor Search End"){
            console.log("Sponsor found: ", await getSponsor());
            return;
        }
        response = await fetch(`${apiBaseUrl}/seekSponsor2`, { method: "POST" });
        result = await response.text();

        console.log("Seek Sponsor Question 2: ", result);
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(phase != "Seek Sponsor 3" && phase != "Sponsor Search End"){
            phase = await getGamePhase();
        }
        if (await getGamePhase() == "Sponsor Search End"){
            console.log("Sponsor found: ", await getSponsor());
            return;
        }
        response = await fetch(`${apiBaseUrl}/seekSponsor3`, { method: "POST" });
        result = await response.text();

        console.log("Seek Sponsor Question 3: ", result);
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(phase != "Seek Sponsor 4" && phase != "Sponsor Search End"){
            phase = await getGamePhase();
        }
        if (await getGamePhase() == "Sponsor Search End"){
            console.log("Sponsor found: ", await getSponsor());
            return;
        }
        response = await fetch(`${apiBaseUrl}/seekSponsor4`, { method: "POST" });
        result = await response.text();

        console.log("Seek Sponsor Question 4: ", result);
        OUTPUT_DIV.innerText += result;
        OUTPUT_DIV.scrollTop = OUTPUT_DIV.scrollHeight;
        //a possible game phase change has occurred tha requires user input. Wait until it's done
        while(phase != "Sponsor Search End"){
            phase = await getGamePhase();
        }
            console.log("Sponsor found: ", await getSponsor());
    } catch (error) {
        console.error("Error in seeking sponsor", error);
    }
}

