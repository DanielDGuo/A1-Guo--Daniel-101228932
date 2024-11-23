from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

try:
    # use find_element to get properties of js things
    # then write js to code it up
    driver.get("file:///C:/Program Files/Github/Comp 4004/A1-DanielGuo-101228932/WebBasedFrontEnd/index.html")
    time.sleep(2)

    text_input = driver.find_element(By.CSS_SELECTOR, "#player-cards .card") #element name, styling. TODO: change this
    output_text = driver.find_element(By.CSS_SELECTOR, "output-window").text
    assert output_text == "foo"
    time.sleep(2)

finally:
    driver.quit()