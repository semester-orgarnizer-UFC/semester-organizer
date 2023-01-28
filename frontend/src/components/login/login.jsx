import React from "react";
import { useState } from "react";
import "./login.css";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { FcGoogle } from "react-icons/fc";
import { FaGithub, FaLinkedin } from "react-icons/fa";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { Box } from "@mui/material";
import { Link, Navigate } from "react-router-dom";
import { login } from "../../services/auth-service.js";
import { useNavigate } from "react-router-dom";
import useCustomSnackbar from "../snackbar/use-custom-snackbar.js";

function Login() {
  const snackbar = useCustomSnackbar();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  async function handleSubmit() {
    const data = {
      email: email,
      password: password,
    };
    const response = await login(data);

    if (response.status === 200) {
      return navigate("/dashboard");
    }
    snackbar.showError(response.message);
  }

  return (
    <ThemeProvider theme={theme}>
      <Box className="content">
        <div className="login-wrap">
          <h2>Login</h2>
          <TextField
            fullWidth
            sx={{ mt: 2 }}
            variant="filled"
            label="Email"
            color="primary"
            value={email}
            onChange={handleEmailChange}
            required
            minLength={4}
            maxLength={4}
          ></TextField>
          <TextField
            fullWidth
            sx={{ mt: 2 }}
            variant="filled"
            label="Senha"
            value={password}
            onChange={handlePasswordChange}
            color="primary"
            required
          ></TextField>
          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              flexDirection: "row",
            }}
          >
            <h3>Esqueceu sua senha?</h3>
            <Link to="/signup">
              <h3>Cadastre-se aqui</h3>
            </Link>
          </Box>
          <Button
            variant="contained"
            fullWidth
            color="primary"
            sx={{ mt: 2 }}
            onClick={handleSubmit}
          >
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

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              gap: 2,
            }}
          >
            <FaGithub color="var(--primary)" />
            <FaLinkedin color="var(--primary)" />
          </Box>
        </div>
      </Box>
    </ThemeProvider>
  );
}

export default Login;
