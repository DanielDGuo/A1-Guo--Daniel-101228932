
const { Builder, By, until } = require('selenium-webdriver');

//for A1_Scenario
async function runTestT1() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(By.id('rigForT1'));
        let input = await driver.findElement(By.id('textInput'));
        let submitButton = await driver.findElement(By.id('submitButton'));

        await startButton.click();
        await driver.sleep(100);

        //sponsor is P2
        await input.sendKeys("N");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);

        //stage 1
        await input.sendKeys("1");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("7");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm stage
        await submitButton.click();
        await driver.sleep(100);
        //stage 2
        await input.sendKeys("2");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("5");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm stage
        await submitButton.click();
        await driver.sleep(100);
        //stage 3
        await input.sendKeys("2");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("3");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("5");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm stage
        await submitButton.click();
        await driver.sleep(100);
        //stage 4
        await input.sendKeys("2");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("3");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm stage
        await submitButton.click();
        await driver.sleep(100);

        //participants stage 1
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);

        //participants all need to draw 1 and discard
        //P1
        await submitButton.click();
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("1");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //P3
        await submitButton.click();
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("1");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //P4
        await submitButton.click();
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("1");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);

        //each player now makes attacks
        //P1 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("5");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("5");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);

        //P3 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("5");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("4");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);

        //P4 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("4");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("6");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);

        //participants stage 2
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);

        //each player now makes attacks
        //P1 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("7");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("6");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);

        //P3 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("9");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("4");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);

        //P4 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("6");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("6");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);

        //participants stage 3
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);

        //each player now makes attacks
        //P3 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("9");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("7");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("5");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);
        //P4 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("7");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("6");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("6");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);

        //participants stage 4
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);

        //each player now makes attacks
        //P3 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("7");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("6");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("6");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);

        //P4 attack
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("4");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("4");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("4");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("5");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //confirm attack
        await submitButton.click();
        await driver.sleep(100);

        //sponsor draw and discard
        await submitButton.click();
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("1");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("1");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("1");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("1");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        //TODO: turn has ended; assert

    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.sleep(30000);
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


        await startButton.click();
        await driver.sleep(100);

        await input.sendKeys("TEST TEST TEST");
        await driver.sleep(100);
        await submitButton.click();

    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.sleep(5000);
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


        await startButton.click();
        await driver.sleep(100);

        await input.sendKeys("TEST TEST TEST");
        await driver.sleep(100);
        await submitButton.click();

    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.sleep(5000);
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


        await startButton.click();
        await driver.sleep(100);

        //sponsor is P2
        await input.sendKeys("N");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);
        await input.sendKeys("Y");
        await driver.sleep(100);
        await submitButton.click();
        await driver.sleep(100);

    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.sleep(5000);
        await driver.quit();
    }
}

async function runAllTests() {
    try{
    await runTestT1();
    //await runTestT2();
    //await runTestT3();
    //await runTestT4();
    } catch (error) {
         console.error("Test encountered an error:", error);
    }
}

runAllTests();