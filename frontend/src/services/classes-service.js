import axios from "axios";

export async function findAllClasses() {
  let config = {
    method: "GET",
    url: "http://localhost:8080/classes",
    responseType: "json",
    headers: { "content-type": "application/json" },
  };

  try {
    return await axios(config);
  } catch (error) {
    return error.response.data;
  }
}
