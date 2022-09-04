import axios from "axios";

  export async function createUser(data) {
    let config = {
      method: "get",
      url: "http://localhost:8080/users",
      data: data,
      responseType: "json",
      headers: { "content-type": "application/json" },
    };

    try {
      let response = await axios(config);
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.log(error);
    }
}


