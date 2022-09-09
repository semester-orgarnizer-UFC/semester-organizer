import axios from "axios";
import { getToken } from "./local-storage";
import { API_URL } from "./utils /api-constants";

const URL = API_URL + "/semester";

export async function findAll() {
  let config = {
    method: "GET",
    url: URL,
    responseType: "json",
    headers: {
      "content-type": "application/json",
      Authorization: "Bearer " + getToken(),
    },
  };

  try {
    return await axios(config);
  } catch (error) {
    return error.response.data;
  }
}

export async function createEmptySemester() {
  let config = {
    method: "POST",
    url: URL,
    responseType: "json",
    headers: {
      "content-type": "application/json",
      Authorization: "Bearer " + getToken(),
    },
  };

  try {
    return await axios(config);
  } catch (error) {
    return error.response.data;
  }
}
