import React from "react";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { FcGoogle } from "react-icons/fc";
import { FaClock } from "react-icons/fa";
import { theme } from "./../../theme.js";
import { ThemeProvider } from "@emotion/react";
import { Box } from "@mui/material";
import { Card, CardContent, Typography, CardActions } from "@mui/material";

const ClassCard = () => (
  <ThemeProvider theme={theme}>
    <Card
      sx={{
        background: "#F1DAC4",
      }}
    >
      <CardContent>
        <Typography sx={{ fontSize: 20 }} color="primary">
          Fundamentos de programação
        </Typography>
        <Typography variant="h5" component="div"></Typography>
        <Typography color="secondary">CC - Ciência da computação</Typography>
        <Box
          sx={{
            display: "flex",
            justifyContent: "end",
            mt: 1,
          }}
        >
          <FaClock size={14} color="#474973"></FaClock>
          <Typography
            variant="h5"
            sx={{ fontSize: 14, ml: 0.5 }}
            color="primary"
          >
            64h
          </Typography>

          <Typography
            variant="h5"
            sx={{ fontSize: 14, ml: 0.5 }}
            color="primary"
          ></Typography>
        </Box>
      </CardContent>
    </Card>
  </ThemeProvider>
);

ClassCard.propTypes = {};

ClassCard.defaultProps = {};

export default ClassCard;
