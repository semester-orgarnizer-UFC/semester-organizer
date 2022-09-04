import React from "react";
import "./dashboard.css";
import { Box, Fab, ThemeProvider } from "@mui/material";
import CardWrap from "./../card-wrap/card-wrap.jsx";
import ClassesNotTaken from "../classes-not-taken/classes-not-taken";
import AddIcon from "@mui/icons-material/Add";
import { theme } from "./../../theme.js";

const data = [
  {
    name: "Fundamentos de programação",
    id: "QXD0001",
    hours: 64,
  },
  {
    name: "Programção orientada a objetos",
    id: "QXD0007",
    hours: 64,
  },
];
const Dashboard = () => (
  <ThemeProvider theme={theme}>
    <Box className="dashboard">
      <Box
        sx={{
          display: "flex",
          gap: "10px",
        }}
      >
        <CardWrap index={1} data={data} />
        <CardWrap index={2} data={data} />
        <ClassesNotTaken />
      </Box>
    </Box>
  </ThemeProvider>
);

export default Dashboard;
