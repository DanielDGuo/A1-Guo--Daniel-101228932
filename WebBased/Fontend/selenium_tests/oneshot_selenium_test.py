import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time

class AdventureGameTest(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install))
        cls.driver.get("file:///C:/Program Files/Github/Comp 4004/A1-DanielGuo-101228932/WebBasedFrontEnd/index.html")
        cls.driver.maximize_window()
        time.sleep(2)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()
    
    def test_1(self):
        text_input = self.driver.find_element(By.CSS_SELECTOR, "#player-cards .card") #element name, styling. TODO: change this
        output_text = self.driver.find_element(By.CSS_SELECTOR, "output-window").text
        self.assertEqual(output_text, "foo", "output message")
        time.sleep(2)

if __name__ == "main":
    unittest.main()