import axios from "axios";
import { AUTHORIZATION_HEADERS } from "./utils /api-constants";
import { API_URL } from "./utils /api-constants";

const URL = API_URL + "/classes";

export async function findAllClasses() {
  let config = {
    method: "GET",
    url: URL,
    responseType: "json",
    headers: { "content-type": "application/json" },
  };

  try {
    return await axios(config);
  } catch (error) {
    return error.response.data;
  }
}
