import axios from "axios";
import { saveToken } from "./local-storage";

export async function login(data) {
  let config = {
    method: "POST",
    url: "http://localhost:8080/login",
    data: JSON.stringify(data),
    responseType: "json",
    headers: { "content-type": "application/json" },
  };

  try {
    const response = await axios(config);
    saveToken(response.headers.authorization);
    return response;
  } catch (error) {
    return error.response.data;
  }
}
