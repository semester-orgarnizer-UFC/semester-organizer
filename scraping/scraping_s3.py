from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def scraping(loginInput, passwordInput):
    opts = webdriver.FirefoxOptions()
    opts.headless = True
    driver = webdriver.Firefox(options=opts)
    driver.get("https://si3.ufc.br/sigaa/verTelaLogin.do")
    login = driver.find_element(By.NAME, "user.login")
    login.send_keys(loginInput)
    password = driver.find_element(By.NAME, "user.senha")
    password.send_keys(passwordInput)
    driver.find_element(By.NAME, "entrar").click()

    WebDriverWait(driver, 30).until(
        EC.presence_of_element_located((By.ID, "modulos"))
    )

    driver.find_element(
        By.XPATH, "//span[contains(text(),'Portal do Discente')]").click()

    driver.find_element(By.ID, "cmAction-0").click()

    driver.find_element(
        By.XPATH, "//td[contains(text(),'Consultas do Discente')]").click()

    driver.find_element(
        By.XPATH, "//td[contains(text(),'Progresso no Curso')]").click()

    cursados = driver.find_elements(By.CLASS_NAME, "cursado")

    disciplinas = []

    for cursado in cursados:
        id = cursado.get_attribute("id")
        query = id.replace("rect", "text")
        element = driver.find_element(By.ID, query)
        disciplinas.append(element.text)

    driver.close()
    return disciplinas
