import axios from "axios";

const api = axios.create({
  baseURL: `http://localhost:8080`,
});

  export async function createUser() {
    let config = {
      method: "post",
      url: api + "/users",
      responseType: "json",
      headers: { "content-type": "application/json" },
      data: {
        name: "Rene",
        surname: "Junior",
        email: "renejr.arraes286@gmail.com",
        password: "rene",
        course: {
          ref: "Course",
          id: "QXD_CC",
        },
        currentSemester: 1,
      },
    };

    try {
      let response = await axios(config);
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.log(error);
    }
}

export default api;
