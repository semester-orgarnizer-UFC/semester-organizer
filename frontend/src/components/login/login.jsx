import React from "react";
import "./login.css";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { FcGoogle } from "react-icons/fc";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { Box } from "@mui/material";

const Login = () => (
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
          Login
        </Button>
        <Button
          type="button"
          color="primary"
          fullWidth
          variant="outlined"
          startIcon={<FcGoogle />}
          sx={{ mt: 2, mb: 5 }}
        >
          Log in With Google
        </Button>
      </div>
    </Box>
  </ThemeProvider>
);

Login.propTypes = {};

Login.defaultProps = {};

export default Login;
