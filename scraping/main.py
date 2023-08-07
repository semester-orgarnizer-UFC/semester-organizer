from fastapi import FastAPI
from login_dto import LoginDto
from scraping_s3 import scraping
import uvicorn

app = FastAPI()

@app.post("/scraping")
async def create_item(loginDto: LoginDto):
    return scraping(loginDto.login, loginDto.password)

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8888)