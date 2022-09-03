import React from "react";
import "./dashboard.css";
import { Card, CardContent, Typography, Box, Container } from "@mui/material";
import CardWrap from "./../card-wrap/card-wrap.jsx";
import ClassesNotTaken from "../classes-not-taken/classes-not-taken";

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
);

export default Dashboard;
