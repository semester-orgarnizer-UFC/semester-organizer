from fastapi import FastAPI
from login_dto import LoginDto
from scraping_s3 import scraping

app = FastAPI()

@app.post("/scraping")
async def create_item(loginDto: LoginDto):
    return scraping(loginDto.login, loginDto.password)