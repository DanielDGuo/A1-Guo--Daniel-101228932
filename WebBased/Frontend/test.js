
const { Builder, By, until } = require('selenium-webdriver');

//for A1_Scenario
async function runTestT1() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://127.0.0.1:8081');

        let startButton = await driver.findElement(driver.findElement(By.id('rigForT1')));
        await startButton.click();
//
//        await driver.sleep(1000);
//        console.log("Game started.");
        await driver.sleep(100000);

//        let hitButton = await driver.findElement(By.xpath("//button[contains(text(), 'Hit')]"));
//        for (let i = 0; i < 3; i++) {
//            await hitButton.click();
//            await driver.sleep(1000);
//            let hitStatus = await driver.findElement(By.id('game-status')).getText();
//            console.log(`After Hit ${i + 1}:`, hitStatus);
//
//            if (hitStatus.includes("Bust")) {
//                console.log("Player busted!");
//                break;
//            }
//        }
//
//        let standButton = await driver.findElement(By.xpath("//button[contains(text(), 'Stand')]"));
//        await standButton.click();
//
//        await driver.wait(until.elementTextContains(driver.findElement(By.id('game-status')), 'Player'), 10000);
//
//        let finalStatus = await driver.findElement(By.id('game-status')).getText();
//        console.log("Final Game Status:", finalStatus);
//
//        if (finalStatus.includes("Player wins") || finalStatus.includes("Dealer wins") || finalStatus.includes("It's a tie")) {
//            console.log("Test passed: Final game result is displayed correctly.");
//        } else {
//            console.log("Test failed: Final game result is missing or incorrect.");
//        }

    } catch (error) {
        console.error("Test encountered an error:", error);
    } finally {
        await driver.quit();
    }
}

runTestT1();