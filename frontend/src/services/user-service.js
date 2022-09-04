import axios from "axios";

  export async function createUser(data) {
    let config = {
      method: "POST",
      url: "http://localhost:8080/users",
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


