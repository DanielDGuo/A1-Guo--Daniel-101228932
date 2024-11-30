
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
        await driver.sleep(1000);

        await input.sendKeys("TEST TEST TEST");
        await driver.sleep(1000);
        await submitButton.click();

    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.sleep(5000);
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
        await driver.sleep(1000);

        await input.sendKeys("TEST TEST TEST");
        await driver.sleep(1000);
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
        await driver.sleep(1000);

        await input.sendKeys("TEST TEST TEST");
        await driver.sleep(1000);
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
        await driver.sleep(1000);

        await input.sendKeys("TEST TEST TEST");
        await driver.sleep(1000);
        await submitButton.click();

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
    await runTestT2();
    await runTestT3();
    await runTestT4();
    } catch (error) {
         console.error("Test encountered an error:", error);
    }
}

runAllTests();