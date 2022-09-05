import React from "react";
import { useState } from "react";
import "./login.css";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import Alert from "@mui/material/Alert";
import Snackbar from "@mui/material/Snackbar";
import { FcGoogle } from "react-icons/fc";
import { FaGithub, FaLinkedin } from "react-icons/fa";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { Box } from "@mui/material";
import { Link, Navigate } from "react-router-dom";
import { login } from "../../services/auth-service.js";
import { useNavigate } from "react-router-dom";
import { saveToken } from "../../services/local-storage.js";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState("");
  const [open, setOpen] = React.useState(false);

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpen(false);
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

    setErrorMessage(response.message);
    setOpen(true);
  }

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
          <Link to="/dashboard">
          <Button
            variant="contained"
            fullWidth
            color="primary"
            sx={{ mt: 2 }}
          >
            Entrar sem banco de dados
          </Button>
          </Link>
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
          {open && (
            <Stack spacing={2} sx={{ width: "100%" }}>
              <Snackbar
                anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
                open={open}
                autoHideDuration={6000}
                onClose={handleClose}
              >
                <Alert
                  onClose={handleClose}
                  severity="error"
                  sx={{ width: "100%" }}
                >
                  {errorMessage}
                </Alert>
              </Snackbar>
            </Stack>
          )}
        </div>
      </Box>
    </ThemeProvider>
  );
}

export default Login;
