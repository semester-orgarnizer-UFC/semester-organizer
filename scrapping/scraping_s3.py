from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from login_dto import LoginDto


def scraping(loginDto: LoginDto):
    opts = webdriver.FirefoxOptions()
    opts.headless = True
    driver = webdriver.Firefox(options=opts)
    driver.get("https://si3.ufc.br/sigaa/verTelaLogin.do")
    login = driver.find_element(By.NAME, "user.login")
    login.send_keys(loginDto.login)
    password = driver.find_element(By.NAME, "user.senha")
    password.send_keys(loginDto.password)
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

    finishedSubjects = driver.find_elements(By.CLASS_NAME, "cursado")

    subjects = []

    for cursado in finishedSubjects:
        id = cursado.get_attribute("id")
        query = id.replace("rect", "text")
        element = driver.find_element(By.ID, query)
        subjects.append(element.text)

    result = {
        "id": loginDto.id,
        "subjects": subjects
    }
    
    driver.close()
    return result
