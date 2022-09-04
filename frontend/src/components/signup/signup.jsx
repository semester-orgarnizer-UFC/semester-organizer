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

  const handleCourseChange = (event) => {
    setCourse(event.target.value);
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
          <Grid container spacing={2} sx={{ mt: 2 }}>
            <Grid item xs={4}>
              <TextField
                variant="filled"
                label="Nome"
                color="primary"
                fullWidth
              ></TextField>
            </Grid>
            <Grid item xs={4}>
              <TextField
                fullWidth
                variant="filled"
                labelId="demo-simple-select-standard-label"
                label="Sobrenome"
                color="primary"
              ></TextField>
            </Grid>
            <Grid item xs={4}>
              <FormControl fullWidth variant="filled">
                <InputLabel id="demo-simple-select-label">Curso</InputLabel>
                <Select
                  color="primary"
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={course}
                  label="Curso"
                  onChange={handleCourseChange}
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
          ></TextField>
          <TextField
            fullWidth
            sx={{ mt: 2 }}
            variant="filled"
            label="Senha"
            color="primary"
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
            onClick={() => createUser()}
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
