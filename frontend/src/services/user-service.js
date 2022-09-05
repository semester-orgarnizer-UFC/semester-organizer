import axios from "axios";
import { getToken } from "./local-storage";
import { API_URL } from "./utils /api-constants";
import { AUTHORIZATION_HEADERS } from "./utils /api-constants";


const URL = API_URL + "/users"

  export async function createUser(data) {
    let config = {
      method: "POST",
      url: URL,
      data: JSON.stringify(data),
      responseType: "json",
      headers: { "content-type": "application/json" },
    };

    try {
      return await axios(config);
    } catch (error) {
      return error.response.data;
    }
}

export async function findNotTakenClasses() {
  let config = {
    method: "GET",
    url: URL + "/classes/nottaken/",
    responseType: "json",
    headers: { "content-type": "application/json", "Authorization" : "Bearer " +  getToken()},
  };

  try {
    return await axios(config);
  } catch (error) {
    return error.response.data;
  }
}


