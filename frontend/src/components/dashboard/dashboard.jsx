import React from "react";
import "./dashboard.css";
import { Box, SpeedDial, ThemeProvider, SpeedDialIcon } from "@mui/material";
import CardWrap from "./../card-wrap/card-wrap.jsx";
import ClassesNotTaken from "../classes-not-taken/classes-not-taken";
import { theme } from "./../../theme.js";
import BottomBar from "../BottomBar/BottomBar";
import AddSemester from "../add-semester/add-semester";

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

const data2 = [
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
  {
    name: "Programção orientada a objetos",
    id: "QXD0007",
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
    <Box
      sx={{
        display: "flex",
        flexDirection: "row",
        alignItems: "start",
        justifyContent: "start",
        gap: 2,
        height: '90vh'
      }}
    >
      <CardWrap index={1} data={data} />
      <CardWrap index={2} data={data2} />
      <AddSemester />
      <ClassesNotTaken />
    </Box>
    <BottomBar></BottomBar>
  </ThemeProvider>
);

export default Dashboard;
