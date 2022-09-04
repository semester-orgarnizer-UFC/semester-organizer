import axios from "axios";

  export async function createUser(data) {
    let config = {
      method: "get",
      url: "http://localhost:8080/users",
      responseType: "json",
      headers: { "content-type": "application/json" },
      data: data
    };

    try {
      let response = await axios(config);
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.log(error);
    }
}


