
const { Builder, By, until } = require('selenium-webdriver');

//for A1_Scenario
async function runTestT1() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.id('rigForT1'));
        let input = await driver.findElement(By.id('textInput'));
        let submitButton = await driver.findElement(By.id('submitButton'));
        let stats = await driver.findElement(By.id('gameStatsOutput'));

        await startButton.click();
        await driver.sleep(10);

        //sponsor is P2
        await input.sendKeys("N");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //stage 1
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 2
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 3
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("3");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 4
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("3");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 1
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //participants all need to draw 1 and discard
        //P1
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P3
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P4
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P1 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 2
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P1 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("9");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 3
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("9");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);
        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 4
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //sponsor draw and discard
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //Turn has ended; assert
        let statsText = await stats.getText();
        console.assert(statsText.includes("P1 Shields: 0"), "Error in P1 Shield Count");
        console.assert(statsText.includes("P2 Shields: 0"), "Error in P2 Shield Count");
        console.assert(statsText.includes("P3 Shields: 0"), "Error in P3 Shield Count");
        console.assert(statsText.includes("P4 Shields: 4"), "Error in P4 Shield Count");

        console.assert(statsText.includes("P1 Hand: F5, F10, F15, F15, F30, H10, B15, B15, L20"), "Error in P1 Hand Contents");
        //console.assert(statsText.includes("P2 Hand: "), "Error in P2 Hand Contents");
        //hand is random; only asset the number of cards
        console.assert(statsText.includes("P3 Hand: F5, F5, F15, F30, S10"), "Error in P3 Hand Contents");
        console.assert(statsText.includes("P4 Hand: F15, F15, F40, L20"), "Error in P4 Hand Contents");

        console.assert(statsText.includes("P1 Hand Size: 9"), "Error in P1 Hand Size");
        console.assert(statsText.includes("P2 Hand Size: 12"), "Error in P2 Hand Size");
        console.assert(statsText.includes("P3 Hand Size: 5"), "Error in P3 Hand Size");
        console.assert(statsText.includes("P4 Hand Size: 4"), "Error in P4 Hand Size");
    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.sleep(20000);
        await driver.quit();
    }
}
//for 2winner_game_2winner_quest
async function runTestT2() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.id('rigForT2'));
        let input = await driver.findElement(By.id('textInput'));
        let submitButton = await driver.findElement(By.id('submitButton'));
        let stats = await driver.findElement(By.id('gameStatsOutput'));

        await startButton.click();
        await driver.sleep(10);

        //sponsor is P1
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //stage 1
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 2
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 3
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 4
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 1
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //participants all need to draw 1 and discard
        //P2
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P3
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P4
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 2
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 3
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 4
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //sponsor draw and discard
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //next turn
        await submitButton.click();
        await driver.sleep(10);

        //sponsor is P3
        await input.sendKeys("N");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);


        //stage 1
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 2
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("3");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 3
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 1
        await input.sendKeys("N");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 2
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 3
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("10");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("10");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //sponsor draw and discard
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //game has ended; assert
        let statsText = await stats.getText();
        console.assert(statsText.includes("P1 Shields: 0"), "Error in P1 Shield Count");
        console.assert(statsText.includes("P2 Shields: 7"), "Error in P2 Shield Count");
        console.assert(statsText.includes("P3 Shields: 0"), "Error in P3 Shield Count");
        console.assert(statsText.includes("P4 Shields: 7"), "Error in P4 Shield Count");

        console.assert(statsText.includes("P1 Hand: F15, F15, F20, F20, F20, F20, F25, F25, F30, H10, B15, L20"), "Error in P1 Hand Contents");
        console.assert(statsText.includes("P2 Hand: F10, F15, F15, F25, F30, F40, F50, L20, L20"), "Error in P2 Hand Contents");
        console.assert(statsText.includes("P3 Hand: F20, F40, D5, D5, S10, H10, H10, H10, H10, B15, B15, L20"), "Error in P3 Hand Contents");
        console.assert(statsText.includes("P4 Hand: F15, F15, F20, F25, F30, F50, F70, L20, L20"), "Error in P4 Hand Contents");

        console.assert(statsText.includes("P1 Hand Size: 12"), "Error in P1 Hand Size");
        console.assert(statsText.includes("P2 Hand Size: 9"), "Error in P2 Hand Size");
        console.assert(statsText.includes("P3 Hand Size: 12"), "Error in P3 Hand Size");
        console.assert(statsText.includes("P4 Hand Size: 9"), "Error in P4 Hand Size");

        console.assert(statsText.includes("Players P2, P4 Won."), "Error in Winners");
    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.sleep(20000);
        await driver.quit();
    }
}

//for 1winner_game_with_events
async function runTestT3() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.id('rigForT3'));
        let input = await driver.findElement(By.id('textInput'));
        let submitButton = await driver.findElement(By.id('submitButton'));
        let stats = await driver.findElement(By.id('gameStatsOutput'));

        await startButton.click();
        await driver.sleep(10);

        //sponsor is P1
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //stage 1
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 2
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 3
        await input.sendKeys("3");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 4
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 1
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //participants all need to draw 1 and discard
        //P2
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P3
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P4
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("3");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("3");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 2
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 3
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("8");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("8");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("9");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 4
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("10");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("10");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("11");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //sponsor draw and discard
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //end the turn
        await submitButton.click();
        await driver.sleep(10);

        //end the turn - plague needs no intervention
        await submitButton.click();
        await driver.sleep(10);

        //players all need to draw 2 and discard - prosperity
        //P1
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P2
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P3
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P4
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //end the turn
        await submitButton.click();
        await driver.sleep(10);

        //P4 draws 2 discards 2 to queen's
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //end the turn
        await submitButton.click();
        await driver.sleep(10);

        //sponsor is P1
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //stage 1
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 2
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 3
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 1
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //participants all need to draw 1 and discard
        //P2
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P3
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P4
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("9");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("9");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("10");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 2
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("10");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("9");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("10");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("7");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 3
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("10");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("11");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //sponsor draw and discard
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //game has ended; assert
        let statsText = await stats.getText();
        console.assert(statsText.includes("P1 Shields: 0"), "Error in P1 Shield Count");
        console.assert(statsText.includes("P2 Shields: 5"), "Error in P2 Shield Count");
        console.assert(statsText.includes("P3 Shields: 7"), "Error in P3 Shield Count");
        console.assert(statsText.includes("P4 Shields: 4"), "Error in P4 Shield Count");

        console.assert(statsText.includes("P1 Hand: F25, F25, F35, D5, D5, S10, S10, S10, S10, H10, H10, H10"), "Error in P1 Hand Contents");
        console.assert(statsText.includes("P2 Hand: F15, F25, F30, F40, S10, S10, S10, H10, E30"), "Error in P2 Hand Contents");
        console.assert(statsText.includes("P3 Hand: F10, F25, F30, F40, F50, S10, S10, H10, H10, L20"), "Error in P3 Hand Contents");
        console.assert(statsText.includes("P4 Hand: F25, F25, F30, F50, F70, D5, D5, S10, S10, B15, L20"), "Error in P4 Hand Contents");

        console.assert(statsText.includes("P1 Hand Size: 12"), "Error in P1 Hand Size");
        console.assert(statsText.includes("P2 Hand Size: 9"), "Error in P2 Hand Size");
        console.assert(statsText.includes("P3 Hand Size: 10"), "Error in P3 Hand Size");
        console.assert(statsText.includes("P4 Hand Size: 11"), "Error in P4 Hand Size");

        console.assert(statsText.includes("Players P3 Won."), "Error in Winners");
    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.sleep(20000);
        await driver.quit();
    }
}
//for 0_winner_quest
async function runTestT4() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.id('rigForT4'));
        let input = await driver.findElement(By.id('textInput'));
        let submitButton = await driver.findElement(By.id('submitButton'));
        let stats = await driver.findElement(By.id('gameStatsOutput'));

        await startButton.click();
        await driver.sleep(10);

        //sponsor is P1
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //stage 1
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("2");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("3");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("5");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("6");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);
        //stage 2
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm stage
        await submitButton.click();
        await driver.sleep(10);

        //participants stage 1
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("Y");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);


        //participants all need to draw 1 and discard
        //P2
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P3
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("4");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //P4
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("3");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //each player now makes attacks
        //P2 attack
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("12");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P3 attack
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //P4 attack
        await submitButton.click();
        await driver.sleep(10);
        //confirm attack
        await submitButton.click();
        await driver.sleep(10);

        //sponsor draw and discard
        await submitButton.click();
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);
        await input.sendKeys("1");
        await driver.sleep(10);
        await submitButton.click();
        await driver.sleep(10);

        //game has ended; assert
        let statsText = await stats.getText();
        console.assert(statsText.includes("P1 Shields: 0"), "Error in P1 Shield Count");
        console.assert(statsText.includes("P2 Shields: 0"), "Error in P2 Shield Count");
        console.assert(statsText.includes("P3 Shields: 0"), "Error in P3 Shield Count");
        console.assert(statsText.includes("P4 Shields: 0"), "Error in P4 Shield Count");

        console.assert(statsText.includes("P1 Hand: F15, D5, D5, D5, D5, S10, S10, S10, H10, H10, H10, H10"), "Error in P1 Hand Contents");
        console.assert(statsText.includes("P2 Hand: F5, F5, F10, F15, F15, F20, F20, F25, F30, F30, F40"), "Error in P2 Hand Contents");
        console.assert(statsText.includes("P3 Hand: F5, F5, F10, F15, F15, F20, F20, F25, F25, F30, F40, L20"), "Error in P3 Hand Contents");
        console.assert(statsText.includes("P4 Hand: F5, F5, F10, F15, F15, F20, F20, F25, F25, F30, F50, E30"), "Error in P4 Hand Contents");

        console.assert(statsText.includes("P1 Hand Size: 12"), "Error in P1 Hand Size");
        console.assert(statsText.includes("P2 Hand Size: 11"), "Error in P2 Hand Size");
        console.assert(statsText.includes("P3 Hand Size: 12"), "Error in P3 Hand Size");
        console.assert(statsText.includes("P4 Hand Size: 12"), "Error in P4 Hand Size");
    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.sleep(20000);
        await driver.quit();
    }
}

async function runAllTests() {
    try{
        await runTestT1();
        await runTestT2();
        await runTestT3();
        await runTestT4();
    } catch (error) {
         console.error("Test encountered an error:", error);
    }
}

runAllTests();