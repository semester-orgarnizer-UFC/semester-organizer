import React from 'react';
import './login.css';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import { FcGoogle } from "react-icons/fc";

const Login = () => (
  <Box
  sx={{
    background: "#000000"
  }}
  fullWidth
  >
    <Container
      sx={{
        background: "#000000",
      }}
    >
      <span className="login-form-title">Bem vindo</span>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          background: "#2e2e2e",
          padding: "20px",
        }}
      >
        <TextField
          id="filled-basic"
          label="E-mail institucional"
          variant="filled"
          fullWidth
        />
        <TextField
          variant="filled"
          name="semester-password"
          type="password"
          label="Senha"
          id="semester-password"
          fullWidth
        />
        <Button variant="contained" fullWidth>
          {" "}
          Login
        </Button>
        <Button
          type="button"
          fullWidth
          variant="outlined"
          color="primary"
          startIcon={<FcGoogle />}
        >
          Log in With Google
        </Button>
      </Box>
    </Container>
  </Box>
);

Login.propTypes = {};

Login.defaultProps = {};

export default Login;
