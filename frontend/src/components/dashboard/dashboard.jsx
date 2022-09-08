import React, { useEffect } from "react";
import "./dashboard.css";
import { Box, Container, ThemeProvider, SpeedDialIcon } from "@mui/material";
import CardWrap from "./../card-wrap/card-wrap.jsx";
import ClassesNotTaken from "../classes-not-taken/classes-not-taken";
import { theme } from "./../../theme.js";
import BottomBar from "../BottomBar/BottomBar";
import AddSemester from "../add-semester/add-semester";
import {
  ClassesContext,
  SemesterProvider,
} from "../../providers/classes-provider";

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
function Dashboard() {
  return (
    <Container
      sx={{
        display: "flex",
        flexDirection: "row",
        alignItems: "start",
        gap: 2,
        overflowX: "scroll",
        height: "90vh",
      }}
    >
      <CardWrap index={1} data={data} />
      <SemesterProvider>
        <AddSemester />
        <ClassesNotTaken />
      </SemesterProvider>
      <BottomBar />
    </Container>
  );
}

export default Dashboard;
