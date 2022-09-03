import React from "react";
import "./login.css";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { FcGoogle } from "react-icons/fc";
import { FaGithub, FaLinkedin } from "react-icons/fa";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { Box, Container } from "@mui/material";

const Login = () => (
  <ThemeProvider theme={theme}>
  <Container className="body">
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <div className="login-wrap">
        <h2>Login</h2>
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
          <h3>Esqueceu sua senha?</h3>
          <h3>Cadastre-se aqui</h3>
        </Box>
        <Button variant="contained" fullWidth color="primary" sx={{ mt: 2 }}>
          Entrar
        </Button>
        <Button
          type="button"
          color="primary"
          fullWidth
          variant="outlined"
          startIcon={<FcGoogle />}
          sx={{ mt: 2, mb: 5 }}
        >
          Entrar com google
        </Button>

        <Box sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          gap: 2
        }}>
          <FaGithub color="#161B33"/>
          <FaLinkedin color="#161B33"/>
        </Box>
      </div>
    </Box>
    </Container>
  </ThemeProvider>
);

Login.propTypes = {};

Login.defaultProps = {};

export default Login;
