import React from "react";
import "./signup.css";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { FcGoogle } from "react-icons/fc";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { Box, Grid } from "@mui/material";
import { Link } from "react-router-dom";
import { createUser } from "../services/user-service";
import { get } from "../services/user-service";


const Signup = () => (
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
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <TextField
              sx={{ mt: 2 }}
              variant="filled"
              label="Nome"
              color="primary"
            ></TextField>
          </Grid>
          <Grid item xs={6}>
            {" "}
            <TextField
              sx={{ mt: 2 }}
              variant="filled"
              label="Sobrenome"
              color="primary"
            ></TextField>
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
          label="Password"
          color="primary"
        ></TextField>
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            flexDirection: "row",
          }}
        >
          <div></div>
          <Link to="/login">
            <h3>Faça login</h3>
          </Link>
        </Box>
        <Button variant="contained" fullWidth color="primary" sx={{ mt: 2 }} onClick= { () => createUser() }>
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

Signup.propTypes = {};

Signup.defaultProps = {};

export default Signup;
