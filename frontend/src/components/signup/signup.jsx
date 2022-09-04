import React from "react";
import { useState } from "react";
import "./signup.css";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { FcGoogle } from "react-icons/fc";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import {
  Box,
  Grid,
  Select,
  MenuItem,
  InputLabel,
  FormControl,
} from "@mui/material";
import { Link } from "react-router-dom";
import { createUser } from "../services/user-service";

function Signup() {
  const [firstName, setName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [course, setCourse] = useState("");
  const [password, setPassword] = useState("");

  const handleNameChange = (event) => {
    setName(event.target.value);
  };
  const handleLastNameChange = (event) => {
    setLastName(event.target.value);
  };
  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleCourseChange = (event) => {
    setCourse(event.target.value);
  };

  const handleSubmit = () => {
    const data = {
      firstName: firstName,
      lastName: lastName,
      password: password,
      email: email,
      course: {
        ref: "Course",
        id: course,
      },
      currentSemester: 1,
    };
    createUser(data);
  };

  return (
    <ThemeProvider theme={theme}>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <div className="login-wrap">
          <h2>Cadastro</h2>
          <Grid spacing={2} sx={{ mt: 2}} className="grid">
            <Grid item xs={4}>
              <TextField
                variant="filled"
                label="Nome"
                color="primary"
                fullWidth
                value={firstName}
                onChange={handleNameChange}
                required
              ></TextField>
            </Grid>
            <Grid item xs={4}>
              <TextField
                fullWidth
                variant="filled"
                labelId="demo-simple-select-standard-label"
                label="Sobrenome"
                color="primary"
                value={lastName}
                onChange={handleLastNameChange}
                required
              ></TextField>
            </Grid>
            <Grid item xs={4}>
              <FormControl fullWidth variant="filled">
                <InputLabel id="demo-simple-select-label">Curso</InputLabel>
                <Select
                fullWidth
                  color="primary"
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={course}
                  label="Curso"
                  onChange={handleCourseChange}
                  required
                >
                  <MenuItem value={"QXD-CC"}>Ciência da computação</MenuItem>
                  <MenuItem value={"QXD-EC"}>Engenharia da computação</MenuItem>
                  <MenuItem value={"QXD-ES"}>Engenharia de software</MenuItem>
                </Select>
              </FormControl>
            </Grid>
          </Grid>
          <TextField
            fullWidth
            sx={{ mt: 2 }}
            variant="filled"
            label="Email"
            color="primary"
            value={email}
            onChange={handleEmailChange}
            required
          ></TextField>
          <TextField
            fullWidth
            sx={{ mt: 2 }}
            variant="filled"
            label="Senha"
            color="primary"
            value={password}
            onChange={handlePasswordChange}
            required
          ></TextField>
          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              flexDirection: "row",
              mt: 2,
            }}
          >
            <div></div>
            <Link to="/login">
              <h3>Faça login</h3>
            </Link>
          </Box>
          <Button
            variant="contained"
            fullWidth
            color="primary"
            sx={{ mt: 2 }}
            onClick={() => handleSubmit()}
          >
            Criar
          </Button>
          <Button
            type="button"
            color="primary"
            fullWidth
            variant="outlined"
            startIcon={<FcGoogle />}
            sx={{ mt: 2, mb: 5 }}
          >
            Criar com google
          </Button>
        </div>
      </Box>
    </ThemeProvider>
  );
}

export default Signup;
