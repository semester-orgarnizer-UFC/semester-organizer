from pydantic import BaseModel

class LoginDto(BaseModel):
    id: str
    login: str 
    password: str
